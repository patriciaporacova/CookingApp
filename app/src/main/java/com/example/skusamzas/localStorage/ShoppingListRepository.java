package com.example.skusamzas.localStorage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingListRepository {

    private ShoppingListItemDao listDao;
    private static ShoppingListRepository instance;
    private LiveData<List<ShoppingListItem>> allItems;

    private ShoppingListRepository(Application aplication) {
        ShoppingListDatabse databse = ShoppingListDatabse.getInstance(aplication);
        listDao = databse.listItemDao();
        allItems = listDao.getAllItems();
    }

    public static synchronized ShoppingListRepository getInstance(Application application)
    {
        if(instance == null)
            instance = new ShoppingListRepository(application);

        return instance;
    }

    public LiveData<List<ShoppingListItem>> getAllItems(){
        return allItems;
    }

    public void insert(ShoppingListItem item) {
        new InsertNoteAsync(listDao).execute(item);
    }

    public void deleteAllItems(){
        new DeleteAllNotesAsync(listDao).execute();
    }

    public void deleteItem(ShoppingListItem item){
        new DeleteItemAsyncTask(listDao).execute(item);
    }

    private static class InsertNoteAsync extends AsyncTask<ShoppingListItem,Void,Void> {
        private ShoppingListItemDao itemDao;
        private InsertNoteAsync(ShoppingListItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ShoppingListItem... listItem) {
            itemDao.insert(listItem[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsync extends AsyncTask<Void,Void,Void> {
        private ShoppingListItemDao itemDao;
        private DeleteAllNotesAsync(ShoppingListItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAllItems();
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<ShoppingListItem, Void, Void> {

        private ShoppingListItemDao itemDao;
        private DeleteItemAsyncTask(ShoppingListItemDao itemDao) {
            this.itemDao = itemDao;
        }


        @Override
        protected Void doInBackground(ShoppingListItem... listItem) {
            itemDao.delete(listItem[0]);
            return null;
        }
    }
}
