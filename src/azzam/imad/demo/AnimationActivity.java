package azzam.imad.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimationActivity extends Activity {

	private SoundPool thePool;
	private int soundId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		
		thePool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundId = thePool.load(this, R.raw.hohoho, 1);
		
	}
	
	public void doAnimationDown(View v) {
		ImageView iv = (ImageView)findViewById(R.id.santa);
		
		iv.animate().translationYBy(100).setDuration(500);
	}
	
	public void doAnimationUp(View v) {
		ImageView iv = (ImageView)findViewById(R.id.santa);
		
		iv.animate().translationYBy(-100).setDuration(500);
	}
	
	public void doAnimationLeft(View v) {
		ImageView iv = (ImageView)findViewById(R.id.santa);
		
		iv.animate().translationXBy(-100).setDuration(500);
	}
	
	public void doAnimationRight(View v) {
		ImageView iv = (ImageView)findViewById(R.id.santa);
		
		iv.animate().translationXBy(100).setDuration(500);
	}
	
	public void doAnimationMagic(View v) {
		final ImageView iv = (ImageView)findViewById(R.id.santa);
		
		final Activity thisActivty = this;
		
		iv.animate().rotationBy(360).alpha(0.2f).setDuration(500).setListener(new AnimatorListenerAdapter() {
			@Override
            public void onAnimationEnd(Animator animation) {
				iv.setRotation(0f);
                iv.animate().alpha(1.0f).setDuration(500).setListener(null);
                
                AudioManager audioManager = (AudioManager) thisActivty.getSystemService(Context.AUDIO_SERVICE);
                float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float leftVolume = curVolume/maxVolume;
                float rightVolume = curVolume/maxVolume;

        		thePool.play(soundId, leftVolume, rightVolume, 1, 0, 1f);
            }
		});
	}
 
}
