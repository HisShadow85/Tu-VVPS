import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * Georgi Andreev
 * there is no main method
 * no errors found
 * 
 */

public class Register implements IpRegister {
	private IpConverter converter;
	private SiteVerification verify;
	private HashMap<Integer, HashMap<String, Integer>> ipVisitedSites = new HashMap<Integer, HashMap<String, Integer>>();
	private HashMap<String, HashMap<Integer, Integer>> siteVisitingIps = new HashMap<String, HashMap<Integer, Integer>>();

	public Register(IpConverter converter,SiteVerification verify) {
		this.converter = converter;
		this.verify = verify;
	}

	/* (non-Javadoc)
	 * @see IpRegister#add(java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String site, String ip) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		Integer intIp = converter.ipFromSringToInteger(ip);
		addSiteToIp(site, intIp);
		addIpToSite(site, intIp);
	}

	private void addSiteToIp(String site, Integer ip) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		int newNumberOfVisitation = 1;
		if (ipVisitedSites.containsKey(ip)) {
			HashMap<String, Integer> setOfsites = ipVisitedSites.get(ip);

			if (setOfsites.containsKey(site)) {
				newNumberOfVisitation += setOfsites.get(site);

			}
			setOfsites.put(site, newNumberOfVisitation);
		} else {
			HashMap<String, Integer> setOfsites = new HashMap<String, Integer>();
			setOfsites.put(site, newNumberOfVisitation);
			ipVisitedSites.put(ip, setOfsites);
		}
	}

	private void addIpToSite(String site, Integer ip) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		int newNumberOfVisitation = 1;
		if (siteVisitingIps.containsKey(site)) {
			HashMap<Integer, Integer> ipSetForCurrentSite = siteVisitingIps.get(site);

			if (ipSetForCurrentSite.containsKey(ip)) {
				newNumberOfVisitation += ipSetForCurrentSite.get(ip);
			}
			ipSetForCurrentSite.put(ip, newNumberOfVisitation);
		} else {
			HashMap<Integer, Integer> ipSetForCurrentSite = new HashMap<Integer, Integer>();
			ipSetForCurrentSite.put(ip, newNumberOfVisitation);
			siteVisitingIps.put(site, ipSetForCurrentSite);
		}
	}

	public List<String> getVisitedSitesByIp(int ip) {
		List<String> resultSet = null;
		if (ipVisitedSites.containsKey(ip)) {
			resultSet = new ArrayList<String>(ipVisitedSites.get(ip).keySet());
		}
		return resultSet;
	}

	public List<String> getVisitedSitesByIp(String ip) {
		return getVisitedSitesByIp(converter.ipFromSringToInteger(ip));
	}

	/* (non-Javadoc)
	 * @see IpRegister#getVisitedSitesByIpAndNumberOfVisitations(int)
	 */
	@Override
	public Map<String, Integer> getVisitedSitesByIpAndNumberOfVisitations(int ip) {
		Map<String, Integer> resultMap = null;
		if (ipVisitedSites.containsKey(ip)) {
			resultMap = Collections.unmodifiableMap(ipVisitedSites.get(ip));
		}
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see IpRegister#getVisitedSitesByIpAndNumberOfVisitations(java.lang.String)
	 */
	@Override
	public Map<String, String> getVisitedSitesByIpAndNumberOfVisitations(String ip) {
		Integer intIp = converter.ipFromSringToInteger(ip);
		Map<String, Integer> tempMap = getVisitedSitesByIpAndNumberOfVisitations(intIp);
		Map<String, String> result = new HashMap<>();
		for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
			result.put(entry.getKey(), converter.ipFromIntegerToString(entry.getValue()));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see IpRegister#getIpsAsIntegerVititedAGivenSite(java.lang.String)
	 */
	@Override
	public List<Integer> getIpsAsIntegerVititedAGivenSite(String site) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		List<Integer> resultSet = null;
		if (siteVisitingIps.containsKey(site)) {
			HashMap<Integer, Integer> ipSet = siteVisitingIps.get(site);
			resultSet = new ArrayList<Integer>(ipSet.keySet());
		}

		return resultSet;
	}

	/* (non-Javadoc)
	 * @see IpRegister#getIpsAsStringVititedAGivenSite(java.lang.String)
	 */
	@Override
	public List<String> getIpsAsStringVititedAGivenSite(String site) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		List<String> resultSet = null;
		if (siteVisitingIps.containsKey(site)) {
			HashMap<Integer, Integer> ipSet = siteVisitingIps.get(site);
			resultSet = new ArrayList<String>(ipSet.size());
			for (Integer var : ipSet.keySet()) {
				resultSet.add(converter.ipFromIntegerToString(var));
			}
		}

		return resultSet;
	}

	/* (non-Javadoc)
	 * @see IpRegister#getIpsVisitetionOnGivanSite(java.lang.String)
	 */
	@Override
	public Map<String, Integer> getIpsVisitetionOnGivanSite(String site) throws MalformedURLException {
		site = verify.getVerifiedSite(site);
		HashMap<String, Integer> resultMap = null;
		if (siteVisitingIps.containsKey(site)) {
			HashMap<Integer, Integer> ipSet = siteVisitingIps.get(site);
			resultMap = new HashMap<String, Integer>();
			for(Map.Entry<Integer, Integer> entry : ipSet.entrySet() ){
				resultMap.put(converter.ipFromIntegerToString(entry.getKey()), entry.getValue());
			}
		}
		
		return resultMap;
	}

}
