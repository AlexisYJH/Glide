package com.example.glide.options;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.glide.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author AlexisYin
 */
@GlideExtension
public class MyGlideExtension {
    private MyGlideExtension() {
    }

    @GlideOption
    public static BaseRequestOptions<?> applyAvatar(BaseRequestOptions<?> options, int size) {
        /*MultiTransformation multi = new MultiTransformation< Bitmap >(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));*/

        BlurTransformation transformation = new BlurTransformation(25, 2);

        return options.placeholder(R.mipmap.img_avatar_default)
                .error(R.mipmap.img_avatar_default)
                .override(size)
                //.centerCrop()
                //与circleCrop不能同时使用，都是BitmapTransformation，使用MultiTransformation
                //.transform(transformation)
                .circleCrop()
                //多种转换
                //.transform(multi)
                //.transform(new MultiTransformation<>(transformation, new CircleCrop()))
                ;
    }

    private static final RequestOptions DECODE_TYPE_GIF = GlideOptions.decodeTypeOf(GifDrawable.class).lock();

    @NonNull
    @GlideType(GifDrawable.class)
    public static RequestBuilder<GifDrawable> gif(RequestBuilder<GifDrawable> requestBuilder) {
        return requestBuilder.transition(new DrawableTransitionOptions()).apply(DECODE_TYPE_GIF);
    }

    @GlideType(Bitmap.class)
    public static RequestBuilder<Bitmap> fade(RequestBuilder<Bitmap> requestBuilder) {
        return requestBuilder.transition(BitmapTransitionOptions.withCrossFade());
    }

}
