package com.zhy.libviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

/**
 * SpannableString 构造器
 * eg:
 * SpannableStringBuilder builder = new SpannableStringBuilder();
 * builder.append("【无格式start 空格】")
 * .append("【指定字体颜色，大小】",Color.RED,12);
 * builder.append("【指定多种格式，大小45px，字体颜色Green，背景颜色Gray,下划线，删除线，粗斜体】",
 * new TextSpan().setAbsoluteSize(50)
 * .setUnderline()
 * .setStrikethrough()
 * .setStyle(Typeface.BOLD_ITALIC)
 * .setForegroundColor(Color.GREEN)
 * .setBackgroundColor(Color.GRAY));
 * //添加图片
 * builder.append(new ImageSpan(getResources().getDrawable(R.drawable.ic_launcher)));
 * builder.append("【此处可点击】",new TextSpan().setClickable(new View.OnClickListener() {
 * <p>
 * public void onClick(View v) {
 * Toast.makeText(SpannableString.this, ((TextView)v).getText().toString(), Toast.LENGTH_LONG).show();
 * }
 * }));
 * builder.append("【无格式end】");
 * //设置点击后的颜色为透明，否则会一直出现
 * textView.setHighlightColor(Color.TRANSPARENT);
 * //开始响应点击事件
 * textView.setMovementMethod(LinkMovementMethod.getInstance());
 * textView.setText(builder.getSpannableString());
 * <p>
 * Created by zhy on 2017/7/26.
 */
public class SpannableStringBuilder {

    private android.text.SpannableStringBuilder spannableStringBuilder;

    public SpannableStringBuilder() {
        this.spannableStringBuilder = new android.text.SpannableStringBuilder();
    }

    /**
     * 添加文字，无格式
     *
     * @param str
     * @return
     */
    public SpannableStringBuilder append(String str) {
        spannableStringBuilder.append(str);
        return this;
    }

    /**
     * 添加字符串，并指定颜色和字体大小
     *
     * @param str   字符串
     * @param color 字体颜色
     * @param size  字体大小，像素单位
     * @return
     */
    public SpannableStringBuilder append(String str, int color, int size) {
        append(str, new TextSpan().setForegroundColor(color).setAbsoluteSize(size));
        return this;
    }

    /**
     * 添加字符串，并指定颜色
     *
     * @param str   字符串
     * @param color 字体颜色
     * @return
     */
    public SpannableStringBuilder append(String str, int color) {
        append(str, new TextSpan().setForegroundColor(color));
        return this;
    }

    /**
     * 添加字符串，并指定字体大小
     *
     * @param str      字符串
     * @param fontsize 字体大小
     * @return
     */
    public SpannableStringBuilder append_(String str, int fontsize) {
        append(str, new TextSpan().setAbsoluteSize(fontsize));
        return this;
    }

    /**
     * 添加格式文本
     *
     * @param str      字符串
     * @param spanText 格式
     * @return
     */
    public SpannableStringBuilder append(String str, TextSpan spanText) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        if (null == spanText) {
            spannableStringBuilder.append(str);
            return this;
        }

        int start, end;
        start = spannableStringBuilder.length();
        spannableStringBuilder.append(str);
        end = spannableStringBuilder.length();
        setSpanText(spanText, start, end);

        return this;
    }

    /**
     * 添加图片
     *
     * @param imageSpan
     * @return
     */
    /*public SpannableStringBuilder append(ImageSpan imageSpan) {
        if (null == imageSpan || null == imageSpan.drawable) {
            return this;
        }
        spannableStringBuilder.append("*");    //先添加一个字符占位，用于被图片替换
        spannableStringBuilder.setSpan(new android.text.style.ImageSpan(imageSpan.drawable, imageSpan.verticalAlignment),
                spannableStringBuilder.length() - 1, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }*/
    public SpannableStringBuilder append(TranslateImageSpan imageSpan) {
        if (imageSpan != null) {
            spannableStringBuilder.append("*");    //先添加一个字符占位，用于被图片替换
            spannableStringBuilder.setSpan(imageSpan,
                    spannableStringBuilder.length() - 1, spannableStringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return this;
    }

    /**
     * 获取一组字符序列，可用于显示在TextView,EditText
     *
     * @return
     */
    public CharSequence getSpannableString() {
        return spannableStringBuilder;
    }

    @Override
    public String toString() {
        return spannableStringBuilder.toString();
    }

    /**
     * 根据SpanText 已有属性，设置各种样式
     *
     * @param spanText 样式载体
     * @param start    样式文本的开始位置
     * @param end      样式文本的结束位置
     */
    private void setSpanText(TextSpan spanText, int start, int end) {
        //7.区块点击事件
        if (spanText.clickableSpan != null) {
            spannableStringBuilder.setSpan(spanText.clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //1.字体颜色
        if (spanText.hasForegroundColor) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(spanText.foregroundColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //2.背景色
        if (spanText.hasBackgroundColor) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(spanText.backgroundColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //3.字体大小
        if (spanText.size > 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(spanText.size, spanText.isDip), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //4.下划线
        if (spanText.isUnderline) {//new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            spannableStringBuilder.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //5.删除线
        if (spanText.isStrikethrough) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //6.字体样式,粗体，斜体...
        if (spanText.style != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(spanText.style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }


    /**
     * SpannableString属性详解	http://blog.csdn.net/lan410812571/article/details/9083023
     * <p>
     * 1、BackgroundColorSpan 背景色
     * 2、ClickableSpan 文本可点击，有点击事件
     * 3、ForegroundColorSpan 文本颜色（前景色）
     * 4、MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
     * 5、MetricAffectingSpan 父类，一般不用
     * 6、RasterizerSpan 光栅效果
     * 7、StrikethroughSpan 删除线（中划线）
     * 8、SuggestionSpan 相当于占位符
     * 9、UnderlineSpan 下划线
     * 10、AbsoluteSizeSpan 绝对大小（文本字体）
     * 11、DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
     * 12、ImageSpan 图片
     * 13、RelativeSizeSpan 相对大小（文本字体）
     * 14、ReplacementSpan 父类，一般不用
     * 15、ScaleXSpan 基于x轴缩放
     * 16、StyleSpan 字体样式：粗体、斜体等
     * 17、SubscriptSpan 下标（数学公式会用到）
     * 18、SuperscriptSpan 上标（数学公式会用到）
     * 19、TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
     * 20、TypefaceSpan 文本字体
     * 21、URLSpan 文本超链接
     */

    public static class TextSpan {
        //	protected String str;
        protected int foregroundColor = -1;
        protected int backgroundColor = -1;
        boolean hasForegroundColor, hasBackgroundColor;

        protected int size = -1;
        protected boolean isDip;

        protected int style = -1;
        protected boolean isStrikethrough;
        protected boolean isUnderline;

        protected ClickableSpan clickableSpan;

        public TextSpan() {
        }

		/*public SpanText(String str) {
            this.str = str;
		}*/

        /**
         * 设置字体颜色（前景色）
         *
         * @param color
         */
        public TextSpan setForegroundColor(@ColorInt int color) {
            this.foregroundColor = color;
            hasForegroundColor = true;
            return this;
        }

        /**
         * 设置字体背景颜色
         *
         * @param color
         */
        public TextSpan setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            hasBackgroundColor = true;
            return this;
        }

        /**
         * 设置字体大小
         * @param size 字体大小
         * @param dip 字体大小单位是否为dip,true 表示 dip,false 表示 pixels
         */
        /*
        public void setAbsoluteSize(int size, boolean isDip){
			this.size = size;
			this.isDip = isDip;
		}
		 */

        /**
         * 设置字体大小，单位为像素
         *
         * @param size
         */
        public TextSpan setAbsoluteSize(int size) {
            this.size = size;
            this.isDip = false;
            return this;
        }

        /**
         * 设置样式，粗体、斜体等
         *
         * @param style Typeface.NORMAL,Typeface.BOLD,Typeface.ITALIC,Typeface.BOLD_ITALIC
         */
        public TextSpan setStyle(int style) {
            this.style = style;
            return this;
        }

        /**
         * 设置文字显示删除线（中划线）
         */
        public TextSpan setStrikethrough() {
            this.isStrikethrough = true;
            return this;
        }

        /**
         * 设置文字显示下划线
         */
        public TextSpan setUnderline() {
            this.isUnderline = true;
            return this;
        }

        /**
         * 设置文字可以点击
         *
         * @param onClickListener 点击的响应事件
         */
        /*public TextSpan setClickable(final View.OnClickListener onClickListener) {
            this.clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickListener.onClick(widget);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(0xFF7daee6);
                }
            };
            return this;
        }*/

        /**
         * 设置文字可以点击,点击的背景色透明
         *
         * @param targetView      文字显示控件
         * @param onClickListener 点击的响应事件
         */
        public TextSpan setClickable(TextView targetView, final View.OnClickListener onClickListener) {
            targetView.setMovementMethod(LinkMovementMethod.getInstance());
            targetView.setHighlightColor(0x00000000);
            this.clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickListener.onClick(widget);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };
            return this;
        }


    }

    public static class TranslateImageSpan extends android.text.style.ImageSpan {

        int translateX, translateY;

        private Drawable d;

        public TranslateImageSpan(@NonNull Drawable d, int translateX, int translateY) {
            super(d);
            this.d = d;
            this.d.setBounds(0, 0, this.d.getIntrinsicWidth(), this.d.getIntrinsicHeight());

            this.translateX = translateX;
            this.translateY = translateY;
        }

        public TranslateImageSpan(@NonNull Drawable d) {
            super(d);
            this.d = d;
            this.d.setBounds(0, 0, this.d.getIntrinsicWidth(), this.d.getIntrinsicHeight());
        }


        //一定要在textView里，setTextSize或者xml里指定textSize，否则计算错误

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
            if (translateX == 0 && translateY == 0) {
                Paint.FontMetricsInt fm = paint.getFontMetricsInt();
                Drawable drawable = getDrawable();
                canvas.save();
                int transY = ((y + fm.descent) + (y + fm.ascent)) / 2 - drawable.getBounds().bottom / 2;
                canvas.translate(x, transY);
                drawable.draw(canvas);
                canvas.restore();
            } else {
                Drawable b = this.d;
                Paint.FontMetricsInt fm = paint.getFontMetricsInt();
                int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;//计算y方向的位移
                canvas.save();
                canvas.translate(x + translateX, transY / translateY);//绘制图片位移一段距离
                b.draw(canvas);
                canvas.restore();
            }
        }
    }


}
