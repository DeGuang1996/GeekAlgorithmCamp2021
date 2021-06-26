package week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubdomainVisits {

    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String domain : cpdomains) {
            int count = Integer.parseInt(domain.substring(0, domain.indexOf(" ")));
            String site = domain.substring(domain.indexOf(" ") + 1);
            while (site.length() > 0) {
                hashMap.put(site, hashMap.getOrDefault(site, 0) + count);
                if (site.contains(".")) {
                    site = site.substring(site.indexOf(".") + 1);
                } else {
                    break;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            res.add(hashMap.get(key) + " " + key);
        }
        return res;
    }
}
