
public class Converter implements IpConverter {
	
	
	
    public String ipFromIntegerToString(int ip) {
		StringBuilder sb = new StringBuilder();
		byte[] bytes = getIpBytes(ip);
		int i;
		for (i = 0; i < bytes.length-1; i++) {
			sb.append(Integer.toString(bytes[i])).append('.');
		}
		sb.append(Integer.toString(bytes[i]));
		return sb.toString();
	}
    
	public Integer ipFromSringToInteger(String ip) {
		byte[] bytes =  getIpBytes(ip);
		if(bytes == null){
			return null;
		}
		int val = 0;
		for (int i = 0; i < bytes.length; i++) {
			val <<= 8;
			val |= bytes[i] & 0xff;
		}
		return val;
	}
	
	static private byte[] getIpBytes(int ip){		
		return new byte[] {
			    (byte)((ip >>> 24) & 0xff),
			    (byte)((ip >>> 16) & 0xff),
			    (byte)((ip >>>  8) & 0xff),
			    (byte)((ip       ) & 0xff)
			  };
	}
	
	

	static private byte[] getIpBytes(String ip) {
		String[] bytes = ip.split("\\.");
		if (bytes.length != 4) {
			return null;
		}
		byte[] resultBytes = new byte[4];
		try {
			for (int i = 0; i < resultBytes.length; i++) {
				resultBytes[i] = Byte.parseByte(bytes[i]);
			}
		} catch (Exception e) {
			return null;
		}
		return resultBytes;
	}
}
