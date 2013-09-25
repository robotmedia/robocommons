/*
 * (C) Copyright Robot Media SL (http://robotmedia.net) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.robotmedia.widget;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;

public class CompositeAdapter extends BaseExpandableListAdapter {

	private List<Adapter> mAdapters;
	private Adapter mGroupsAdapter;

	/**
	 * By default is it assumed that child views cannot be reused due to coming from different adapters.
	 * @param groupPosition
	 * @param childPosition
	 * @param convertView
	 * @return True is the given view can be reused for the given group and child. False otherwise.
	 */
	protected boolean isValid(int groupPosition, int childPosition, View convertView) {
		return false;
	}
	
	public CompositeAdapter(Adapter groupsAdapter, List<Adapter> childrenAdapters) {
		mGroupsAdapter = groupsAdapter;
		mAdapters = childrenAdapters;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		final Adapter groupAdapter = mAdapters.get(groupPosition);
		return groupAdapter.getItem(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		final Adapter groupAdapter = mAdapters.get(groupPosition);
		return groupAdapter.getItemId(childPosition);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		final Adapter groupAdapter = mAdapters.get(groupPosition);
		if (convertView != null && !isValid(groupPosition, childPosition, convertView)) {
			convertView = null;
		}
		return groupAdapter.getView(childPosition, convertView, parent);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		final Adapter groupAdapter = mAdapters.get(groupPosition);
		return groupAdapter.getCount();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// What is this method for? It's never called in the Android repos! See: https://github.com/search?l=Java&p=2&q=%22getGroup%28%29%22+%40android&ref=advsearch&type=Code
		return mGroupsAdapter.getItem(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mGroupsAdapter.getCount();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return mGroupsAdapter.getItemId(groupPosition);
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		return mGroupsAdapter.getView(groupPosition, convertView, parent);
	}

	@Override
	public boolean hasStableIds() {
		if (!mGroupsAdapter.hasStableIds()) return false;
		for (Adapter adapter : mAdapters) {
			if (!adapter.hasStableIds()) return false;
		}
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
