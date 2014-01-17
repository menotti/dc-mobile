package br.ufscar.dc.mobile.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DeviceListActivity extends Activity implements OnItemClickListener {
	private ListView deviceList;
	
	private BluetoothAdapter mBluetoothAdapter;
	private ArrayAdapter<String> mArrayAdapter;
	
	public static int REQUEST_ENABLE_BT = 1;
	public static String mUUID = "d17deaaa-94e9-45ae-8ab0-0f73ef29e417";
	public static String NAME = "myBluetoothApp";
	
	/*
	 * Esse é um BroadcastReceiver que captura intents do discovery do bluetooth
	 */
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// Se encontrou um dispositivo
			if(BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
				
			}
		}	
	};
	
	/*
	 * Um handler e runnables para as threads acessarem a UI thread 
	 */
	private String mMessage;
	private final Handler mHandler = new Handler();
	private final Runnable mShowMessage = new Runnable() {
		public void run() {
			showMessage(mMessage);
		}
	};
	private final Runnable mShowDialog = new Runnable() {
		public void run() {
			writeMessage();
			tConnected.cancel();
		}
	};
	
	private IntentFilter filter;
	private ConnectedThread tConnected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_list);
		
		
		// 1. Obter o BluetoothAdapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter == null) {
			//Device does not support Bluetooth
		}
		
		// 2. Habilitar o Bluetooth
		if(!mBluetoothAdapter.isEnabled()){
			Intent enableBluetooth = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);	
		}
		
		mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		// 3. Obter dispositivos pareados
		Set<BluetoothDevice> pairedDevices	= mBluetoothAdapter.getBondedDevices();
		if(pairedDevices.size() > 0){
			for(BluetoothDevice device : pairedDevices){
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}		
		
		// Adiciona o footer à lista
		deviceList = (ListView) findViewById(R.id.deviceList);
		View v = getLayoutInflater().inflate(R.layout.device_list_footer, null);
		Button scan = (Button) v.findViewById(R.id.button_scan);
		
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Scanea por novos dispositivos bluetooth
				mBluetoothAdapter.startDiscovery();
			}
		});
		
		deviceList.addFooterView(v);
		
		// Seta o adaptador
		deviceList.setAdapter(mArrayAdapter);
		
		//Registra o BroadcastReceiver
		//filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		//registerReceiver(mReceiver, filter); //Precisa remover no onDestroy
		
		deviceList.setOnItemClickListener(this);
		
		//4. Inicia a thread que aceita conexões
		AcceptThread tAccept = new AcceptThread();	
		tAccept.start();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == RESULT_OK){
			// Usuário ligou o Bluetooth
			Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_LONG).show();
		} else if(requestCode == RESULT_CANCELED){
			// Erro ou usuário clicou não
			Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);	
		registerReceiver(mReceiver, filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.device_list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
		//Obtemos o endereço clicado na lista
		String address = mArrayAdapter.getItem(pos).split("\n")[1];
		BluetoothDevice mDevice = mBluetoothAdapter.getRemoteDevice(address);
		
		//Conectamos a esse endereço com a ConnectThread
		ConnectThread tConnect = new ConnectThread(mDevice);
		tConnect.start();
	}
	
	/*
	 * Essa thread é responsável por aceitar uma conexão de outro dispositivo
	 * Ela espera uma o socket aceitar uma conexão
	 */
	private class AcceptThread extends Thread {
		private final BluetoothServerSocket mServerSocket;
		
		public AcceptThread(){
			BluetoothServerSocket tmp = null;
			try {
				tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, UUID.fromString(mUUID));
			} catch(IOException e) {
				Log.e("BLUETOOTH", e.toString());
			}
			mServerSocket = tmp;
		}
		
		public void run() {
			BluetoothSocket socket = null;
			// Faz a thread ficar escutando qualquer entrada
			while(true){
				try {
					socket = mServerSocket.accept();
				} catch(IOException e) { break; }
				
				// Se houve uma conexão
				if (socket != null) {
					manageConnection(socket);
					try {
						mServerSocket.close();
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/*
	 * Essa thread é responsável por se conectar a um dispositivo bluetooth
	 * Ela tentará conectar ao endereço fornecido
	 */
	private class ConnectThread extends Thread {
		private final BluetoothSocket mSocket;
		private final BluetoothDevice mDevice;
		
		public ConnectThread(BluetoothDevice device){
			BluetoothSocket tmp = null;
			mDevice = device;
			
			try {
				tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(mUUID));
			} catch (IOException e) {
				Log.e("BLUETOOTH", e.toString());
			}
			mSocket = tmp;
		}
		
		public void run() {
			// Cancela para acelerar a conexão
			mBluetoothAdapter.cancelDiscovery();
			
			try {
				mSocket.connect();
			} catch (IOException openExc) {
				try {
					mSocket.close();
				} catch (IOException closeExc) { }
				return;
			}
			
			manageConnection(mSocket);
			mHandler.post(mShowDialog);
		}
		
		public void cancel() {
			try {
				mSocket.close();
			} catch (IOException e) { }
		}
	}
	
	/*
	 * Essa thread é responsável por gerenciar a conexão, isto é, tentar receber ou enviar alguma mensagem.
	 * Após a conexão ser aberta, ele entrará em um loop em que ficará lendo dados vindos do socket
	 * O método write será usado quando queremos enviar uma mensagem pelo socket. 
	 */
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInput;
		private final OutputStream mmOutput;
		
		public ConnectedThread(BluetoothSocket socket){
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;
			
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream(); 
			} catch (IOException e) { }
			
			mmInput = tmpIn;
			mmOutput = tmpOut;
		}
		
		public void run() {
			byte[] buffer = new byte[1024];
			int bytes;
			
			while(true) {
				try {
					bytes = mmInput.read(buffer);
					//Escreve a string na variavel
					mMessage = new String(buffer);
					mHandler.post(mShowMessage);
				} catch(IOException e) { break; }
			}
		}
		
		public void write(byte[] bytes) {
			try {
				mmOutput.write(bytes);
			} catch(IOException e) { }
		}
		
		public void cancel() {
			try{
				mmSocket.close();
			} catch (IOException e) { }
		}
	}
	
	/*
	 * Esse método apenas cria a thread que gerencia a conexão
	 */
	public void manageConnection(BluetoothSocket socket){
		tConnected = new ConnectedThread(socket);
		tConnected.start();
	}
	
	/*
	 * Esse método deverá ser chamado na UI Thread por um Handler associado
	 * Ele é responsável por mostrar a mensagem recebida.
	 */
	public void showMessage(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	/*
	 * Esse método abre um dialog que permite ao usuário escrever uma mensagem
	 * que será enviada ao dispositivo bluetooth escolhido
	 */
	public void writeMessage() {
		final EditText input = new EditText(this);
		AlertDialog.Builder dialog = new AlertDialog.Builder(this)
			.setTitle("Send a message")
			.setView(input)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String msg = input.getText().toString();
					try{
						tConnected.write(msg.getBytes());
					} catch (NullPointerException e) {
						e.printStackTrace();
						if(tConnected == null) {
							Log.d("BT", "thread is null");
						}
					}
			}
		});
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
		}).show();
	}
}
