package com.lambdaexpr;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class CompanyDTO {
	String companyName;
	String companyStatus;
	List<AddressListDTO> addressList;

	public CompanyDTO(String name, String status, List<AddressListDTO> addresses) {
		this.companyName = name;
		this.companyStatus = status;
		this.addressList = addresses;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public void setAddressList(List<AddressListDTO> addresses) {
		this.addressList = addresses;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}
	
	public List<AddressListDTO> getAddressList() {
		return addressList;
	}

}

class AddressListDTO {
	String addressId;
	String addressStatus;

	public AddressListDTO(String name, String status) {
		this.addressId = name;
		this.addressStatus = status;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressStatus() {
		return addressStatus;
	}

	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

}

public class ModifyCollection {

	public List<CompanyDTO> getCompaniesWithAddresses() {
		
		List<AddressListDTO> c1Address = Arrays.asList(new AddressListDTO("C1V1", "Active"), new AddressListDTO("C1V2", "Active"), new AddressListDTO("C1V3", "InActive"), new AddressListDTO("C1V4", "Active"), new AddressListDTO("C1V5", "Inactive"), new AddressListDTO("C1V6", "Active"));
		List<AddressListDTO> c2Address = Arrays.asList(new AddressListDTO("C2V1", "Active"), new AddressListDTO("C2V2", "Active"), new AddressListDTO("C2V3", "Active"));
		List<AddressListDTO> c3Address = Arrays.asList(new AddressListDTO("C3V1", "Active"), new AddressListDTO("C3V2", "Active"), new AddressListDTO("C3V3", "Active"), new AddressListDTO("C3V4", "Active"), new AddressListDTO("C3V5", "Active"), new AddressListDTO("C3V6", "Active"));
		List<AddressListDTO> c4Address = Arrays.asList(new AddressListDTO("C4V1", "Active"), new AddressListDTO("C4V2", "Active"), new AddressListDTO("C4V3", "Active"), new AddressListDTO("C4V4", "Inactive"), new AddressListDTO("C4V5", "Active"));
		List<AddressListDTO> c5Address = Arrays.asList(new AddressListDTO("C5V1", "Active"), new AddressListDTO("C5V2", "Active"), new AddressListDTO("C5V3", "Active"), new AddressListDTO("C5V4", "Active"));
		List<AddressListDTO> c6Address = Arrays.asList(new AddressListDTO("C6V1", "Active"), new AddressListDTO("C6V2", "Inactive"), new AddressListDTO("C6V3", "Active"));
		
		List<CompanyDTO> companies = Arrays.asList(
									new CompanyDTO("C1", "Active", c1Address), 
									new CompanyDTO("C2", "Active", c2Address), 
									new CompanyDTO("C3", "Active", c3Address), 
									new CompanyDTO("C4", "Active", c4Address), 
									new CompanyDTO("C5", "Active", c5Address), 
									new CompanyDTO("C6", "Active", c6Address)
									);
		
		return companies;		
	}


	public void modifyListUsingIterator(List<AddressListDTO> addresses) {
		System.out.println("\n\nmodifyListUsingIterator() called...");

		Iterator<AddressListDTO> addressesItr = addresses.stream().iterator();
		while (addressesItr.hasNext()) {
			AddressListDTO address = addressesItr.next();
			
			if(address.getAddressStatus().equals("InActive")) {
				address.setAddressId("NewID1");
			}
		}
	}

	
	public void modifyListUsingForEach(List<AddressListDTO> addresses) {
		System.out.println("\n\nmodifyListUsingForEach() called...");

		addresses.stream().forEach(address -> {
			if(address.getAddressStatus().equals("InActive")) {
				address.setAddressId("NewID1");
			}
		});
	}

	
	public void modifyListUsingForIndex(List<AddressListDTO> addresses) {
		System.out.println("\n\nmodifyListUsingForIndex() called...");
		for(int i=0; i< addresses.size(); i++) {
			AddressListDTO address = addresses.get(i);
			
			if(address.getAddressStatus().equals("InActive")) {
				address.setAddressId("NewID1");
				addresses.set(i, address);
			}
		}
	}

	
	public static void main(String[] args) {
		ModifyCollection mainObj = new ModifyCollection();
		List<CompanyDTO> companies = mainObj.getCompaniesWithAddresses();
		Iterator<CompanyDTO> companyItr  = companies.stream().filter(company -> company.getCompanyName().equals("C1")).iterator();
		List<AddressListDTO> addresses = companyItr.next().getAddressList();
		
		System.out.println("\n***Before updating the List***");
		addresses.stream().forEach(address -> {
			System.out.print(address.getAddressId() + " | ");
		});
		
//		mainObj.modifyListUsingIterator(addresses);
//		mainObj.modifyListUsingForEach(addresses);
		mainObj.modifyListUsingForIndex(addresses);
		
		
		System.out.println("\n***After updating the List***");
		addresses.stream().forEach(address -> {
			System.out.print(address.getAddressId() + " | ");
		});

	}
}
