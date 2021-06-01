package com.example.unitalk;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {

    public static final int QUESTION_DIR = 0;

    public static final int QUESTION_ITEM = 1;

    public static final int USERINFO_DIR = 2;

    public static final int USERINFO_ITEM = 3;

    public static final String AUTHORITY = "com.example.unitalk";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "question", QUESTION_DIR);
        uriMatcher.addURI(AUTHORITY, "question/#", QUESTION_ITEM);
        uriMatcher.addURI(AUTHORITY, "userinfo", USERINFO_DIR);
        uriMatcher.addURI(AUTHORITY, "userinfo/#", USERINFO_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "Unitalk.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // 查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case QUESTION_DIR:
                cursor = db.query("Question", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case QUESTION_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Question", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
                break;
            case USERINFO_DIR:
                cursor = db.query("UserInfo", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case USERINFO_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("UserInfo", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case QUESTION_DIR:
            case QUESTION_ITEM:
                long newQuestionId = db.insert("Question", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/question/" + newQuestionId);
                break;
            case USERINFO_DIR:
            case USERINFO_ITEM:
                long newUserId = db.insert("UserInfo", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/userinfo/" + newUserId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // 更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case QUESTION_DIR:
                updatedRows = db.update("Question", values, selection, selectionArgs);
                break;
            case QUESTION_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Question", values, "id = ?", new String[] { bookId });
                break;
            case USERINFO_DIR:
                updatedRows = db.update("UserInfo", values, selection, selectionArgs);
                break;
            case USERINFO_ITEM:
                String userId = uri.getPathSegments().get(1);
                updatedRows = db.update("UserInfo", values, "id = ?", new String[] { userId });
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // 删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case QUESTION_DIR:
                deletedRows = db.delete("Question", selection, selectionArgs);
                break;
            case QUESTION_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Question", "id = ?", new String[] { bookId });
                break;
            case USERINFO_DIR:
                deletedRows = db.delete("UserInfo", selection, selectionArgs);
                break;
            case USERINFO_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("UserInfo", "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case QUESTION_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.unitalk. provider.question";
            case QUESTION_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.unitalk. provider.question";
            case USERINFO_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.unitalk. provider.userinfo";
            case USERINFO_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.unitalk. provider.userinfo";
        }
        return null;
    }

}