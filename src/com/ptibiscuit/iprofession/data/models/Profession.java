package com.ptibiscuit.iprofession.data.models;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class Profession {
	private String name;
	private String tag;
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	private Profession parent;
	
	private ArrayList<Require> prerequis = new ArrayList<Require>();
	
	public Profession(String tag, String name, ArrayList<Skill> skills, ArrayList<Require> prerequis, Profession parent)
	{
		this.name = name;
		this.skills = skills;
		this.tag = tag;
		this.prerequis = prerequis;
		this.parent = parent;
	}
	
	public boolean hasSkill(Skill sk)
	{
		for (Skill s : skills)
		{
			if (sk.equals(s))
				return true;
		}
		// Ok, on va tenter notre profession supérieure, si on en a une.
		if (parent != null)
			return this.parent.hasSkill(sk);
		return false;
	}
	
	public ArrayList<Require> canLearn(Player p)
	{
		ArrayList<Require> incapacitent = new ArrayList<Require>();
		for (Require r : prerequis)
		{
			if (!r.has(p))
				incapacitent.add(r);
		}
		return incapacitent;
	}
	
	public String getName() {
		return name;
	}

	public String getTag() {
		return tag;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public ArrayList<Require> getRequired() {
		return prerequis;
	}

	@Override
	public String toString() {
		return tag;
	}

	public Profession getParent() {
		return parent;
	}
}
