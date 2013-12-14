package azzam.imad.demo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AudioActivity extends Activity {

	private int[] soundIds;
	private SoundPool thePool = null;
	private int index = 0;
	
	private MediaPlayer _player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio);
		
		// Load the sounds into the sound pool so that they can be played quickly and not be loaded on every play operation
		int[] rawIds = new int[4];
		rawIds[0] = R.raw.note0;
		rawIds[1] = R.raw.note1;
		rawIds[2] = R.raw.note2;
		rawIds[3] = R.raw.note3;
		
		thePool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		soundIds = new int[4];
		for(int i = 0; i < 4; i++) {
			soundIds[i] = thePool.load(this, rawIds[i], 1);
		}
		
		// Create the media player for the loger audio file
		_player = MediaPlayer.create(this, R.raw.bird);
		//_player.setOnCompletionListener(this);
	}

	public void playSound(View v) {
		AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = curVolume/maxVolume;
        float rightVolume = curVolume/maxVolume;

		thePool.play(soundIds[index], leftVolume, rightVolume, 1, 0, 1f);
		
		TextView tv = (TextView)findViewById(R.id.sound_played_textview);
		tv.setText("Just played sound " + index + ".");
		
		index = ++index % 4; 
	}
	
	public void playMusic(View v) {
		if(_player.isPlaying()) {
			return;
		}
		
		AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = curVolume/maxVolume;
        float rightVolume = curVolume/maxVolume;
        
        _player.seekTo(0);
        _player.setVolume(leftVolume, rightVolume);
		_player.start();
	}
	
	public void stopMusic(View v) {
		if(_player.isPlaying()) {
			_player.pause();
		}
	}
	
	public void toggleLooping(View v) {
		ToggleButton tb = (ToggleButton) v;
		_player.setLooping(tb.isChecked());
	}
}
