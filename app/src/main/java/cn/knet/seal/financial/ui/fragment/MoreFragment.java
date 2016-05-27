package cn.knet.seal.financial.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.ui.activity.LoginActivity;
import cn.knet.seal.financial.ui.activity.MoreAboutActivity;
import cn.knet.seal.financial.ui.activity.MoreHelpActivity;
import cn.knet.seal.financial.ui.activity.MoreModifyPwdActivity;
import cn.knet.seal.financial.util.CacheUtils;
import cn.knet.seal.financial.util.DialogHelp;
import cn.knet.seal.financial.util.UpdateManager;

/**
 * 更多fragment
 *
 * ClassName: MoreFragment <br/>
 * Date: 2016/5/24 15:51 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class MoreFragment extends Fragment implements View.OnClickListener{

    private TextView mTvAccountName;
    private RelativeLayout mTvLogout;
    private RelativeLayout mRlAbout;
    private RelativeLayout mRlHelp;
    private RelativeLayout mRlModifyPwd;
    private RelativeLayout mRlUpdate;

    public MoreFragment() {
    }

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        initUI(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initUI(View view) {
        mTvAccountName = (TextView) view.findViewById(R.id.tv_account_name);
        mRlModifyPwd = (RelativeLayout) view.findViewById(R.id.rl_password_modify);
        mRlUpdate = (RelativeLayout) view.findViewById(R.id.rl_update_check);
        mRlHelp = (RelativeLayout) view.findViewById(R.id.rl_help);
        mRlAbout = (RelativeLayout) view.findViewById(R.id.rl_about);
        mTvLogout = (RelativeLayout) view.findViewById(R.id.rl_logout);

        mTvAccountName.setText(CacheUtils.get(this.getActivity()).getAsString(KnetConstants.UID));
        mRlModifyPwd.setOnClickListener(this);
        mRlUpdate.setOnClickListener(this);
        mRlHelp.setOnClickListener(this);
        mRlAbout.setOnClickListener(this);
        mTvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_password_modify:
                startActivity(new Intent(getActivity(), MoreModifyPwdActivity.class));
                break;
            case R.id.rl_update_check:
                checkUpdate();
                break;
            case R.id.rl_help:
                startActivity(new Intent(getActivity(), MoreHelpActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(getActivity(), MoreAboutActivity.class));
                break;
            case R.id.rl_logout:
                logout();
                break;
        }
    }

    /**
     * 检查更新
     */
    private void checkUpdate() {
        new UpdateManager(getActivity()).checkUpdate();
    }

    /**
     * 退出登录
     */
    private void logout() {
        DialogHelp.getConfirmDialog(getActivity(), getString(R.string.more_logout_tip), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 清除缓存的记录
                CacheUtils.get(getActivity()).remove(KnetConstants.TOKEN);
                CacheUtils.get(getActivity()).remove(KnetConstants.UID);
                CacheUtils.get(getActivity()).remove(KnetConstants.PWD);
                CacheUtils.get(getActivity()).remove(KnetConstants.PERMISSION);
                // 跳转到登录界面
                // 设置登录标识为false
                startActivity(new Intent(getActivity(),LoginActivity.class));
                KnetAppManager.getAppManager().finishAllActivity();
                getActivity().finish();
            }
        },null).show();
    }
}
