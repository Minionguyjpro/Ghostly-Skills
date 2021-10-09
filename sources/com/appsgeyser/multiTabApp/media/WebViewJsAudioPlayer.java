package com.appsgeyser.multiTabApp.media;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.appsgeyser.multiTabApp.utils.UrlConverter;
import java.io.IOException;

public class WebViewJsAudioPlayer {
    public static String JS_INTERFACE_NAME = "AudioPlayer";
    /* access modifiers changed from: private */
    public String _onLoadListener;
    /* access modifiers changed from: private */
    public String _onTrackFinishedListener;
    /* access modifiers changed from: private */
    public WebView _parentWebView;
    /* access modifiers changed from: private */
    public MediaPlayer _player = new MediaPlayer();

    public WebViewJsAudioPlayer(WebView webView) {
        this._parentWebView = webView;
    }

    @JavascriptInterface
    public void play(String str) {
        String absolute = new UrlConverter(this._parentWebView).toAbsolute(str);
        stop();
        _setPlayerEvenets();
        if (absolute.contains("file://")) {
            _playLocal(absolute);
        } else {
            _playInternet(absolute);
        }
    }

    @JavascriptInterface
    private void _playInternet(String str) {
        try {
            this._player.setDataSource(this._parentWebView.getContext(), Uri.parse(str));
            this._player.setAudioStreamType(3);
            this._player.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    @JavascriptInterface
    private void _playLocal(String str) {
        UrlConverter urlConverter = new UrlConverter(this._parentWebView);
        if (str.contains("file://")) {
            try {
                AssetFileDescriptor fileDescriptor = urlConverter.toFileDescriptor(str);
                this._player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                this._player.setAudioStreamType(3);
                this._player.prepareAsync();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    private void _setPlayerEvenets() {
        this._player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (!(WebViewJsAudioPlayer.this._onLoadListener == null || WebViewJsAudioPlayer.this._onLoadListener.length() == 0)) {
                    WebViewJsAudioPlayer webViewJsAudioPlayer = WebViewJsAudioPlayer.this;
                    webViewJsAudioPlayer._callFunction(webViewJsAudioPlayer._onLoadListener, (String[]) null);
                }
                mediaPlayer.start();
            }
        });
        this._player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mediaPlayer.isPlaying() && WebViewJsAudioPlayer.this._onTrackFinishedListener != null && WebViewJsAudioPlayer.this._onTrackFinishedListener.length() != 0) {
                    WebViewJsAudioPlayer webViewJsAudioPlayer = WebViewJsAudioPlayer.this;
                    webViewJsAudioPlayer._callFunction(webViewJsAudioPlayer._onTrackFinishedListener, (String[]) null);
                }
            }
        });
        this._player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                WebViewJsAudioPlayer.this._player.reset();
                return false;
            }
        });
    }

    @JavascriptInterface
    public void setOnLoadListener(String str) {
        this._onLoadListener = str;
    }

    @JavascriptInterface
    public void setOnTrackFinishedListener(String str) {
        this._onTrackFinishedListener = str;
    }

    /* access modifiers changed from: private */
    @JavascriptInterface
    public void _callFunction(String str, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(str);
        sb.append("(");
        if (strArr != null) {
            int i = 0;
            for (String str2 : strArr) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("'");
                sb.append(str2.replace("'", "'"));
                sb.append("'");
                i++;
            }
        }
        sb.append(");");
        final String sb2 = sb.toString();
        this._parentWebView.post(new Runnable() {
            public void run() {
                WebViewJsAudioPlayer.this._parentWebView.loadUrl(sb2);
            }
        });
    }

    @JavascriptInterface
    public int getDuration() {
        MediaPlayer mediaPlayer = this._player;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return 0;
        }
        return this._player.getDuration();
    }

    @JavascriptInterface
    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this._player;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return 0;
        }
        return this._player.getCurrentPosition();
    }

    @JavascriptInterface
    public void stop() {
        MediaPlayer mediaPlayer = this._player;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this._player.stop();
            }
            this._player.reset();
        }
    }

    @JavascriptInterface
    public void pause() {
        MediaPlayer mediaPlayer = this._player;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this._player.pause();
        }
    }

    @JavascriptInterface
    public void resume() {
        MediaPlayer mediaPlayer = this._player;
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            this._player.start();
        }
    }
}
