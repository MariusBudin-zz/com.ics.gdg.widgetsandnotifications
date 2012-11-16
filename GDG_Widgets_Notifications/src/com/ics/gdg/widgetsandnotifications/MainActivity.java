package com.ics.gdg.widgetsandnotifications;

import android.R.anim;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	/**
	 * Position of the dummy fragments
	 */
	static final int WIDGETS_SECTION = 0, NOTIFICATIONS_SECTION = 1,
			ABOUT_SECTION = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case WIDGETS_SECTION:
				return getString(R.string.title_section1).toUpperCase();
			case NOTIFICATIONS_SECTION:
				return getString(R.string.title_section2).toUpperCase();
			case ABOUT_SECTION:
				return getString(R.string.title_section3).toUpperCase();
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment implements
			OnClickListener {
		public DummySectionFragment() {
		}

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = null;
			Bundle args = getArguments();
			int section = args.getInt(ARG_SECTION_NUMBER);

			switch (section) {
			case WIDGETS_SECTION:
				v = inflater.inflate(R.layout.widgets_layout, null);
				break;
			case NOTIFICATIONS_SECTION:
				v = inflater.inflate(R.layout.notifications_layout, null);

				v.findViewById(R.id.btn_actionsNotification)
						.setOnClickListener(this);
				v.findViewById(R.id.btn_cancelNotification).setOnClickListener(
						this);
				v.findViewById(R.id.btn_expandableNotification)
						.setOnClickListener(this);
				v.findViewById(R.id.btn_indetProgressNotification)
						.setOnClickListener(this);
				v.findViewById(R.id.btn_plainNotification).setOnClickListener(
						this);
				v.findViewById(R.id.btn_progressNotification)
						.setOnClickListener(this);

				break;
			case ABOUT_SECTION:
				v = inflater.inflate(R.layout.about_layout, null);

				v.findViewById(R.id.btnMoreApps).setOnClickListener(this);
				v.findViewById(R.id.twt_marius).setOnClickListener(this);
				v.findViewById(R.id.gplus_marius).setOnClickListener(this);
				v.findViewById(R.id.img_marius).setOnClickListener(this);
				Linkify.addLinks((TextView) v.findViewById(R.id.txtMail),
						Linkify.ALL);

				break;
			default:
				break;
			}
			return v;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			/**
			 * NOTIFICATIONS_SECTION
			 */
			case R.id.btn_actionsNotification:
				MyNotificationCenter.showActionNotification(v.getContext());
				break;

			case R.id.btn_cancelNotification:
				MyNotificationCenter.cancelNotification();
				break;

			case R.id.btn_expandableNotification:
				MyNotificationCenter.showExpandableNotification(v.getContext());
				break;

			case R.id.btn_indetProgressNotification:
				MyNotificationCenter.showIndetProgressNotification(v
						.getContext());
				break;

			case R.id.btn_plainNotification:
				MyNotificationCenter.showPlainNotification(v.getContext());
				break;

			case R.id.btn_progressNotification:
				MyNotificationCenter.showProgressNotification(v.getContext());
				break;

			/**
			 * ABOUT_SECTION
			 */
			case R.id.img_marius:
			case R.id.twt_marius:
				String url = "https://twitter.com/#!/MariusBudin";
				Intent ia = new Intent(Intent.ACTION_VIEW);
				ia.setData(Uri.parse(url));
				startActivity(ia);
				getActivity().overridePendingTransition(anim.fade_in,
						anim.fade_out);

				break;
			case R.id.gplus_marius:
				String urlplus = "https://plus.google.com/117999615691957384244";
				Intent ip = new Intent(Intent.ACTION_VIEW);
				ip.setData(Uri.parse(urlplus));
				startActivity(ip);
				getActivity().overridePendingTransition(anim.fade_in,
						anim.fade_out);

				break;
			case R.id.btnMoreApps:
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("market://search?q=pub:Ics")));
				getActivity().overridePendingTransition(anim.fade_in,
						anim.fade_out);

				break;
			default:
				break;
			}

		}
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
		overridePendingTransition(anim.fade_in, anim.fade_out);
	}
}
