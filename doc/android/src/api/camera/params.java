Parameters params = mCam.getParameters();
params.setJpegQuality(100);

List<Size> sizes = params.getSupportedPictureSizes();
Camera.Size size = sizes.get(0);
for(int i=0;i<sizes.size();i++)
{
	if(sizes.get(i).width > size.width)
		size = sizes.get(i);
}
params.setPictureSize(size.width, size.height);		

mCam.setParameters(params);
