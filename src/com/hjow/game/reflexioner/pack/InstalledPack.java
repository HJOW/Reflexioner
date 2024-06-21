package com.hjow.game.reflexioner.pack;

import java.util.ArrayList;
import java.util.List;

public class InstalledPack
{
	@SuppressWarnings("unchecked")
	public static List<Pack> getPacks() {
		List<String> packClasses = new ArrayList<String>();
		packClasses.add("com.hjow.game.reflexioner.pack.Pack1");
		
		List<Pack> packs = new ArrayList<Pack>();
		for(String cls : packClasses)
		{
			try
			{
				Class<? extends Pack> packClass = (Class<? extends Pack>) Class.forName(cls);
				Pack pack = packClass.newInstance();
				packs.add(pack);
			}
			catch(ClassNotFoundException ex) 
			{}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return packs;
	}
}
