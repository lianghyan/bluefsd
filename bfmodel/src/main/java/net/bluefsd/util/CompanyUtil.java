package net.bluefsd.util;

import java.util.ArrayList;
import java.util.List;

import net.bluefsd.model.CompanyDetail;

public class CompanyUtil {

	public static List<CompanyDetail> composeCompanyDetail(List<Object[]> list) {
		List<CompanyDetail> cdList = new ArrayList<>();
		if (list == null) {
			return cdList;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			CompanyDetail cd = CompanyUtil.composeCompanyDetail(obj);
			cdList.add(cd);
		}
		return cdList;
	}

	public static CompanyDetail composeCompanyDetail(Object[] obj) {
		// select c.companyCd, c.companyName,c.ceoName, c.exchCd, e.exchName,
		// c.director, c.brief,
		// c.sectorCd, s.sectorName
		CompanyDetail cd = new CompanyDetail();
		int index = 0;
		cd.setCompanyCd((String) obj[index++]);
		cd.setCompanyName((String) obj[index++]);
		cd.setCeoName((String) obj[index++]);
		cd.setExchCd((String) obj[index++]);
		cd.setExchName((String) obj[index++]);
		cd.setDirector((String) obj[index++]);
		cd.setBrief((String) obj[index++]);
		cd.setSectorCd((String) obj[index++]);
		cd.setSectorName((String) obj[index++]);
		cd.setStockCd((String) obj[index++]);

		return cd;
	}

}
