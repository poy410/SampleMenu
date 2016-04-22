package com.example.tacademy.samplemenu;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.text_context)); // 롱 클릭을 했을때 컨텍스트메뉴 띄우기 위해 등록
        Button btn=(Button)findViewById(R.id.btn_mode_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSupportActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        mode.getMenuInflater().inflate(R.menu.menu_main,menu);

                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.menu1:
                            case R.id.menu2:
                            case R.id.menu3:
                                Toast.makeText(MainActivity.this,"mode "+ getTitle(),Toast.LENGTH_SHORT).show();
                                mode.finish();
                                return true;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }
                });
            }
        });
        btn=(Button)findViewById(R.id.btn_popup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(MainActivity.this, v);
                popup.inflate(R.menu.popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"popup Show"+item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.text_context :
                getMenuInflater().inflate(R.menu.context_munu,menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu10 :
                Toast.makeText(MainActivity.this,"TExt",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item=menu.findItem(R.id.menu1);
        View view= MenuItemCompat.getActionView(item);
        EditText editText=(EditText)view.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this,"dd",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu1:
            case R.id.menu2:
            case R.id.menu3:
                Toast.makeText(MainActivity.this," Menu : "+item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
