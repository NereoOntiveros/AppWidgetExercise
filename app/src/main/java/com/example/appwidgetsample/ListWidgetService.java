package com.example.appwidgetsample;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import static com.example.appwidgetsample.NewAppWidget.listaPalabras;

public class ListWidgetService extends RemoteViewsService {

    ArrayList<String>remoteViewPalabrasList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext(),intent);
    }

    class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        Context mContext = null;

        public ListRemoteViewsFactory(Context context, Intent intent){
            mContext = context;
        }

        @Override
        public void onCreate() {
            remoteViewPalabrasList = listaPalabras;
        }

        @Override
        public void onDataSetChanged() {
            remoteViewPalabrasList = listaPalabras;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return remoteViewPalabrasList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(),R.layout.widget_item);

            views.setTextViewText(R.id.list_item_view,remoteViewPalabrasList.get(position));

            return views;

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
