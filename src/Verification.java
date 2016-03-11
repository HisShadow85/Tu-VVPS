import java.net.MalformedURLException;
import java.net.URL;

public class Verification implements SiteVerification {
       
	/* (non-Javadoc)
	 * @see SiteVerification#getVerifiedSite(java.lang.String)
	 */
	@Override
	public String getVerifiedSite(String site) throws MalformedURLException{
		return new URL(site).getHost();
	}
}
