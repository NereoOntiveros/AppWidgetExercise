package com.example.appwidgetsample;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static ArrayList<String> listaPalabras = new ArrayList<>();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        /*
        //update each of the app widgets with the remote adapter
        for (int appWidgetId : appWidgetIds) {


            //Set up the intent that starts the ListViewService which will
            //provide the views for this collection
            Intent intent = new Intent(context, ListWidgetService.class);

            //Add the app widget ID to the intent extras
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            //Instantiate the RemoteViews object for the app widget layout
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            //Set up the RemoteViews object to use a RemoteViews adapter.
            //This adapter connects to a RemoteViewsService
            //through the specified intent.
            //This is how data are populated.
            rv.setRemoteAdapter(R.id.list_view, intent);

            //The empty view is displayed when the collection has no items.
            //It should be in the same layout used to instantiate the RemoteViews
            //object above.
            rv.setEmptyView(R.id.list_view, R.id.empty_view);





            appWidgetManager.updateAppWidget(appWidgetId, rv);

        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);*/
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId){
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);

        Intent intent = new Intent(context,ListWidgetService.class);
        views.setRemoteAdapter(R.id.list_view,intent);

        appWidgetManager.updateAppWidget(appWidgetId,views);
    }

    public static void updateWidgets (Context context,AppWidgetManager appWidgetManager,int[]appWidgetIds){
        for(int appWidgetId:appWidgetIds){
            updateAppWidget(context,appWidgetManager,appWidgetId);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,NewAppWidget.class));

        final String action = intent.getAction();

        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){

            listaPalabras = intent.getExtras().getStringArrayList("palabras");
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.list_view);
            //refresh all widgets
            NewAppWidget.updateWidgets(context,appWidgetManager,appWidgetIds);
            super.onReceive(context, intent);
        }
    }
}

