package net.devwiki.devwiki.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextViewActivity extends BaseActivity {

    @BindView(R.id.normal_tv)
    TextView mNormalTv;
    @BindView(R.id.html_tv)
    TextView mHtmlTv;
    @BindView(R.id.drawable_tv)
    TextView mDrawableTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ButterKnife.bind(this);

        String data =
                "<html class=\"no-js\">\n" +
                "<head>\n" +
                "    <title>关于 - DevWiki</title>\n" +
                "</head>\n" +
                "<body>" +
                "<h2>为什么叫DevWiki?</h2>\n" +
                "\n" +
                "<p>我是一个Android开发者,一个外行的程序员.就是因为外行,发现自己需要学的知识有很多,所以给自己整一个开发的Wiki,让自己学的更多!</p>\n" +
                "\n" +
                "<p>目前在教育产业的公司开发Android App,这个网站将作为我的学习,笔记,跳坑记录.如果你有什么问题或者建议,可以联系我.</p>\n" +
                "\n" +
                "<p>联系方式:</p>\n" +
                "\n" +
                "<p>QQ:<a href=\"tencent://Message/?Uin=674952098&amp;websiteName=qzone.qq.com&amp;Menu=yes\"><strong>674952098</strong></a></p>\n" +
                "\n" +
                "<p>GitHub:<a href=\"http://github.com/dev-wiki\"><strong>DevWiki GitHub</strong></a></p>\n" +
                "\n" +
                "<p>新浪微博:<a href=\"http://weibo.com/devwiki\"><strong>DevWiki Weibo</strong></a></p>\n" +
                "\n" +
                "<p>知乎:<a href=\"http://www.zhihu.com/people/devwiki\"><strong>DevWiki ZhiHu</strong></a></p>\n" +
                "\n" +
                "<p>简书:<a href=\"http://www.jianshu.com/users/c9c118608767/latest_articles\"><strong>DevWiki JianShu</strong></a></p>\n" +
                "\n" +
                "<p>微信公共号:</p>\n" +
                "\n" +
                "<p><img src=\"http://7xjhi6.com1.z0.glb.clouddn.com/WeiXin-DevWiki-Common.jpg\" alt=\"微信公共号\" /></p>\n" +
                "</body></html>";
        mHtmlTv.setText(Html.fromHtml(data));
    }
}
