package com.lambdaexpr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Company {
	String companyName;
	String companyStatus;
	List<Vendor> vendors;

	public Company(String name, String status, List<Vendor> vendor) {
		this.companyName = name;
		this.companyStatus = status;
		this.vendors = vendor;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}
	
	public List<Vendor> getVendors() {
		return vendors;
	}

}

class Vendor {
	String vendorName;
	Boolean isActive;

	public Vendor(String name, Boolean isActive) {
		this.vendorName = name;
		this.isActive = isActive;
	}

	public String getVendorName() {
		return vendorName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

}

public class LambdaIterateObj {

	public List<Company> getCompaniesWithVendors() {
		
		List<Vendor> c1Vendor = Arrays.asList(new Vendor("C1V1", false), new Vendor("C1V2", false), new Vendor("C1V3", false), new Vendor("C1V4", false), new Vendor("C1V5", true), new Vendor("C1V6", false));
		List<Vendor> c2Vendor = Arrays.asList(new Vendor("C2V1", false), new Vendor("C2V2", false), new Vendor("C2V3", false));
		List<Vendor> c3Vendor = Arrays.asList(new Vendor("C3V1", false), new Vendor("C3V2", false), new Vendor("C3V3", false), new Vendor("C3V4", false), new Vendor("C3V5", false), new Vendor("C3V6", false));
		List<Vendor> c4Vendor = Arrays.asList(new Vendor("C4V1", false), new Vendor("C4V2", false), new Vendor("C4V3", false), new Vendor("C4V4", true), new Vendor("C4V5", false));
		List<Vendor> c5Vendor = Arrays.asList(new Vendor("C5V1", false), new Vendor("C5V2", false), new Vendor("C5V3", false), new Vendor("C5V4", false));
		List<Vendor> c6Vendor = Arrays.asList(new Vendor("C6V1", false), new Vendor("C6V2", true), new Vendor("C6V3", false));
		
		List<Company> companies = Arrays.asList(
									new Company("C1", "Active", c1Vendor), 
									new Company("C2", "Active", c2Vendor), 
									new Company("C3", "Active", c3Vendor), 
									new Company("C4", "Active", c4Vendor), 
									new Company("C5", "Active", c5Vendor), 
									new Company("C6", "Active", c6Vendor)
									);
		
		return companies;		
	}
	
//	forEach approach; "break" not supported. Stream.forEach is not a loop and it is not designed to be terminated by "break"
	public void useForEach(List<Company> companies) {
		System.out.println("useForEach() called...");
		List<String> activeVendorCompany = new ArrayList<String>();

		companies.forEach(company -> {
			String companyName = company.getCompanyName();
			List<Vendor> vendors = company.getVendors();
			
			vendors.forEach(vendor -> {
				if (vendor.getIsActive()) {
					activeVendorCompany.add(companyName);
				}	
			});
		});

		System.out.println(activeVendorCompany);
	}
	
//	forEach approach; "break" not supported. Stream.forEach is not a loop and it is not designed to be terminated by "break"
	public void useStreamForEach(List<Company> companies) {
		System.out.println("useStreamForEach() called...");
		List<String> activeVendorCompany = new ArrayList<String>();

//		System.out.println(companies.stream().distinct().count());
		
		companies.stream().forEach(company -> {
			String companyName = company.getCompanyName();
			List<Vendor> vendors = company.getVendors();
			
			vendors.forEach(vendor -> {
				if (vendor.getIsActive()) {
					activeVendorCompany.add(companyName);
				}	
			});
		});

		System.out.println(activeVendorCompany);
	}	
	
//	Iterator approach; to support "break";
	public void useIterator(List<Company> companies) {
		System.out.println("useIterator() called...");
		List<String> activeVendorCompany = new ArrayList<String>();
		
		Iterator<Company> companyItr = companies.stream().iterator();
		while (companyItr.hasNext()) {
			Company company = companyItr.next();
			String companyName = company.getCompanyName();
			List<Vendor> vendors = company.getVendors();
			
			Iterator<Vendor> vendorItr = vendors.iterator();
			while (vendorItr.hasNext()) {
				Vendor vendor = vendorItr.next();
				
				if(vendor.getIsActive()) {
					activeVendorCompany.add(companyName);
					break;
				}	
			}
		}		
		
		System.out.println(activeVendorCompany);
	}

//	Iterable<T> is a FunctionalInterface which has only one abstract method iterator(). 
//	So () -> sourceIterator is a lambda expression instantiating an Iterable instance as an anonymous implementation
//	Below code converts Iterator to Stream without creating a copy of a new object for perf reasons
	
	public void convertItrToStream() {
		System.out.println("convertItrToStream() called...");
		Iterator<String> sourceIterator = Arrays.asList("A", "B", "C").iterator();
		Iterable<String> iterable = () -> sourceIterator;
		
		//2nd parameter for stream is for parallel impl. 
		Stream<String> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		
		targetStream.forEach(System.out::println);
		
		/*
		 * Get the Iterator. 
		 * Convert the iterator to Spliterator using Spliterators.spliteratorUnknownSize() method. 
		 * Convert the formed Spliterator into Sequential Stream using StreamSupport.stream() method. 
		 * Return the stream.
		 
		 	// Convert the iterator to Spliterator 
		 	Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(sourceIterator, 0); 
		 	// Get a Sequential Stream from spliterator using StreamSupport
		 	return StreamSupport.stream(spliterator, false); 
		 * 
		 */
		
		/* 
		 * Approach for Java 9; but doesn't support .parallel() streams 
		Stream.generate(() -> null)
	    .takeWhile(x -> sourceIterator.hasNext())
	    .map(n -> sourceIterator.next())
	    .forEach(System.out::println);
	    
	    */
		
		
		/*
		 * Non-performant option - AVOID
		 * 
		 * Collections.list(sourceIterator).stream()..
		 */
	}

//	modify a list value while iterating
	public void modifyIterator(List<Company> companies) {
		System.out.println("modifyIterator() called...");
		
		System.out.println("***Before updating the List***");
		companies.stream().forEach(company -> {
			System.out.println(company.getCompanyName() + " - " + company.getCompanyStatus());
		});		
		
		Iterator<Company> companyItr = companies.stream().iterator();
		while (companyItr.hasNext()) {
			Company company = companyItr.next();
			String companyName = company.getCompanyName();

			if(companyName.equals("C4")) {
//				System.out.println(company.getCompanyStatus());
				company.companyStatus = "InActive";
			}
		}
		
		System.out.println("***After updating the List***");
		companies.stream().forEach(company -> {
			System.out.println(company.getCompanyName() + " - " + company.getCompanyStatus());
		});
	}

	public static void main(String[] args) {
		LambdaIterateObj mainObj = new LambdaIterateObj();
		List<Company> companies = mainObj.getCompaniesWithVendors();
		
//		List<String> listNames = companies.stream().map(c -> c.getCompanyName())
//		        .collect(Collectors.toList());
//		System.out.println(listNames);

//		mainObj.useForEach(companies);
//		mainObj.useStreamForEach(companies);
		mainObj.useIterator(companies);
//		mainObj.convertItrToStream();
		mainObj.modifyIterator(companies);
		
	}
}
