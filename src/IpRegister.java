import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IpRegister {

	void add(String site, String ip) throws MalformedURLException;

	Map<String, Integer> getVisitedSitesByIpAndNumberOfVisitations(int ip);

	Map<String, String> getVisitedSitesByIpAndNumberOfVisitations(String ip);

	List<Integer> getIpsAsIntegerVititedAGivenSite(String site) throws MalformedURLException;

	List<String> getIpsAsStringVititedAGivenSite(String site) throws MalformedURLException;

	Map<String, Integer> getIpsVisitetionOnGivanSite(String site) throws MalformedURLException;
    
	List<String> getVisitedSitesByIp(int ip);

	List<String> getVisitedSitesByIp(String ip);
}