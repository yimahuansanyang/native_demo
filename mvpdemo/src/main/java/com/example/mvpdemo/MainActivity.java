package com.example.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LoginView {

    private Button btn_login;
    private LoginPresenter loginPresenter;
    private TextView tv_content;
    private LoginPresenter2 loginPresenter2;
    private EditText et_userName;
    private EditText et_password;
    private ProgressBar progressBar;
    private int[] array = {57, 68, 59, 52};
    private int[] array1 = {49, 38, 65, 97, 76, 13, 27, 48, 55, 4};
    private int[] array2 = {57, 68, 59, 52, 72, 28, 96, 33, 24, 19};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
//        btn_login = (Button) findViewById(R.id.btn_login);
//        tv_content = (TextView) findViewById(R.id.tv_content);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginPresenter.loadData();
//            }
//        });
//        loginPresenter = new LoginPresenter(this);
        sort();
        shellSort(array1);
        quickSort(array2);
        initview();
        loginPresenter2 = new LoginPresenter2(this);
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array) + " quickSort");
    }

    private static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low]; //数组的第一个作为中轴   
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high]; //比中轴小的记录移到低端       
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];  //比中轴大的记录移到高端 
        }
        list[low] = tmp;//中轴记录到尾    return low;                  //返回中轴的位置
        return low;
    }


    private static void quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);
            //将list数组进行一分为二       
            quickSort(list, low, middle - 1);
            // 对低字表进行递归排序       
            quickSort(list, middle + 1, high);
            //对高字表进行递归排序   
        }
    }


    private void shellSort(int[] array) {
        int i;
        int j;
        int temp;
        int gap = 1;
        int len = array.length;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = array[i];
                for (j = i - gap; j >= 0 && array[j] > temp; j -= gap) {
                    array[j + gap] = array[j];
                    array[j + gap] = temp;
                }
            }
            System.out.println(Arrays.toString(array) + " shellSort");
        }
    }


    private void sort() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) { //将大于temp的值整体后移一个单位             
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        System.out.println(Arrays.toString(array) + " insertSort");


    }

    //点击登录
    public void LoginClick(View view) {
        loginPresenter2.login();
    }

    //点击清除
    public void ClearClick(View view) {
        loginPresenter2.clear();
    }

    private void initview() {
        et_userName = (EditText) findViewById(R.id.main_et_username);
        et_password = (EditText) findViewById(R.id.main_et_password);
        progressBar = (ProgressBar) findViewById(R.id.main_progressBar);
    }


    @Override
    public String getUsername() {
        return et_userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showSuccessMsg(User user) {
        Toast.makeText(MainActivity.this, "User " + user.getUsername() + " Login Sccess!", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showFailedMsg(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void clearEditText() {
        et_userName.setText("");
        et_password.setText("");

    }

//    @Override
//    public void setData(String data) {
//        tv_content.setText(data);
//    }
}

