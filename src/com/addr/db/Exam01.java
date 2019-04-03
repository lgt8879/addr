package com.addr.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.addr.db.impl.AddressDAOImpl;

public class Exam01 {
	private static final String ADDR_FILE_PATH = "D:\\study2\\addr\\jibun_chungnam.txt";
	private static AddressDAO adao = new AddressDAOImpl();

	public static void main(String[] args) {
		Long sTime = System.currentTimeMillis();
		File f = new File(ADDR_FILE_PATH);

		List<String> colList = new ArrayList<>();
		colList.add("ad_code");
		colList.add("ad_sido");
		colList.add("ad_gugun");
		colList.add("ad_dong");
		colList.add("ad_lee");
		colList.add("ad_san");
		colList.add("ad_bunji");
		colList.add("ad_ho");
		colList.add("ad_roadcode");
		colList.add("ad_isbase");
		colList.add("ad_orgnum");
		colList.add("ad_subnum");
		colList.add("ad_jinum");
		colList.add("ad_etc");

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			List<Map<String, String>> addrList = new ArrayList<>();
			int cnt = 1;
			while ((line = br.readLine()) != null) {
				String[] lines = line.split("\\|");
				Map<String, String> addrMap = new HashMap<>();
				for (int i = 0; i < lines.length; i++) {
					addrMap.put(colList.get(i), lines[i]);
				}
				addrList.add(addrMap); 
				if (addrList.size() == 100000) {
					Long subStime = System.currentTimeMillis();
					int result = adao.insertAddressList(addrList);
					addrList.clear();
					System.out.println("반영된 건수 : " + result);
					System.out.println("10000개 insert 완료시간 : " + (System.currentTimeMillis() - subStime));
				}
				
			}
			Long subStime = System.currentTimeMillis();
			int result = adao.insertAddressList(addrList);
			addrList.clear();
			System.out.println("반영된 건수 : " + result);
			System.out.println("나머지 건수 insert 완료시간 : "  + (System.currentTimeMillis() - subStime));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("전체 수행 시간 : " + (System.currentTimeMillis() - sTime));

//		String addr = "4413111600|충청남도|천안시 동남구|신방동||0|95|5|441312249001|0|84|0|43457|";
//		String[] addrs = addr.split("\\|");
//		System.out.println(addrs.length);
//		List<String> colList = new ArrayList<>();
//		colList.add("ad_code");
//		colList.add("ad_sido");
//		colList.add("ad_gugun");
//		colList.add("ad_dong");
//		colList.add("ad_lee");
//		colList.add("ad_san");
//		colList.add("ad_bunji");
//		colList.add("ad_ho");
//		colList.add("ad_roadcode");
//		colList.add("ad_isbase");
//		colList.add("ad_orgnum");
//		colList.add("ad_subnum");
//		colList.add("ad_jinum");
//		colList.add("ad_etc");
//		Map<String, String> addMap = new HashMap<>();
//		for (int i = 0; i < addrs.length; i++) {
//			addMap.put(colList.get(i), addrs[i]);
//		}
	}
}
