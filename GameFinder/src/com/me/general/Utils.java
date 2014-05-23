package com.me.general;

public class Utils {

	public static String getGenName(int genNumber)
	{
		switch (genNumber) {
			case 0: 
				return "Toate";
			case 1:
				return "Action RPG";
			case 2:
				return "Arcade";
			case 3:
				return "First-person shooter";
			case 4:
				return "MMORPG";
			case 5: 
				return "Multiplayer online battle arena";
			case 6:
				return "Racing";
			case 7:
				return "Real-time strategy";
			case 8:
				return "Simulation";
			case 9:
				return "Sports";
			case 10:
				return "Third-person shooter";
			default:
				return null;
		}
	}
	
	public static int getGenNumber(String genName)
	{
		if (genName.equals("Toate"))
			return 0;
		else if (genName.equals("Action RPG"))
			return 1;
		else if (genName.equals("Arcade"))
			return 2;
		else if (genName.equals("First-person shooter"))
			return 3;
		else if (genName.equals("MMORPG"))
			return 4;
		else if (genName.equals("Multiplayer online battle arena"))
			return 5;
		else if (genName.equals("Racing"))
			return 6;
		else if (genName.equals("Real-time strategy"))
			return 7;
		else if (genName.equals("Simulation"))
			return 8;
		else if (genName.equals("Sports"))
			return 9;
		else if (genName.equals("Third-person shooter"))
			return 10;
		else 
			return -1;
	}
	
	public static String getPlatformaName(int platformaNumber)
	{
		switch (platformaNumber) {
			case 0: 
				return "Toate";
			case 1:
				return "PC";
			case 2:
				return "PS3";
			case 3:
				return "PS4";
			case 4:
				return "Xbox 360";
			case 5: 
				return "Xbox One";
			case 6:
				return "Mobile";
			default:
				return null;
		}
	}
	
	public static int getPlatformaNumber(String platformaName)
	{
		if (platformaName.equals("Toate"))
			return 0;
		else if (platformaName.equals("PC"))
			return 1;
		else if (platformaName.equals("PS3"))
			return 2;
		else if (platformaName.equals("PS4"))
			return 3;
		else if (platformaName.equals("Xbox 360"))
			return 4;
		else if (platformaName.equals("Xbox One"))
			return 5;
		else if (platformaName.equals("Mobile"))
			return 6;
		else 
			return -1;
	}
	
	public static boolean isNullOrWhiteSpace(String str) {
		
		return (str == null || str.isEmpty() || str.trim().isEmpty());	
	}
}
