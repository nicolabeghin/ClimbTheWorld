package org.unipd.nbeghin.climbtheworld.adapters;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
	private List<Fragment>	fragments;

	public PagerAdapter(FragmentManager manager, List<Fragment> fragments) {
		super(manager);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

	@Override
	public String getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Buildings";
			case 1:
				return "Tours";
			default:
				return "Undefined";
		}
	}
}
