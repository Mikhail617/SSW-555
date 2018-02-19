package gedcomlint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Individual {
	String id;
	String name;
	String gender;
	String birthDate;
	String alive;
	String deathDate;
	List<String> spouseFamilyIds = new ArrayList<String>();
	List<String> childFamilyIds = new ArrayList<String>();
	
	public String getId() {
		return id.replaceAll("@", "");
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAge() {
		try {
			if(birthDate != null) {
				Date bd =new SimpleDateFormat("dd MMM yyyy").parse(birthDate);
				if(deathDate != null) {
					Date dd =new SimpleDateFormat("dd MMM yyyy").parse(deathDate);
					return ""+ (dd.getYear() - bd.getYear());
				} else {
					return ""+ ((new Date()).getYear() - bd.getYear());	
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "NA";
	}
	public String isAlive() {
		return deathDate == null ? "True" : "False";
	}
	public String getDeathDate() {
		return deathDate == null ? "NA" : deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public List<String> getSpouseFamilyIds() {
		return spouseFamilyIds;
	}

	public String getSpouseFamilyIdsAsString() {
		if( spouseFamilyIds != null && spouseFamilyIds.size() > 0 ) {
			String value = "{";
			boolean first = true;
			for(String str : spouseFamilyIds) {
				if(!first) {
					value += ",";
				}
				value += str.replaceAll("@", "'");
				
				first = false;
			}
			value += "}";
			return value;
		} else {
			return "NA";
		}
	}

	public void setSpouseFamilyIds(List<String> spouseFamilyIds) {
		this.spouseFamilyIds = spouseFamilyIds;
	}
	public List<String> getChildFamilyIds() {
		return childFamilyIds;
	}
	public void setChildFamilyIds(List<String> childFamilyIds) {
		this.childFamilyIds = childFamilyIds;
	}
	public String getChildFamilyIdsAsString() {
		if( childFamilyIds != null && childFamilyIds.size() > 0 ) {
			String value = "{";
			boolean first = true;
			for(String str : childFamilyIds) {
				if(!first) {
					value += ",";
				}
				value += str.replaceAll("@", "'");
				
				first = false;
			}
			value += "}";
			return value;
		} else {
			return "NA";
		}
	}
	
	
	
}
