/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class VerySimplePlayerWithPlayFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private ActionListenerImpl actionListenerImpl;

	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private JPanel controlPanel;
	private JButton playButton;

	public VerySimplePlayerWithPlayFrame() {
		super("Very Simple Player With Play - Java Example by andbin");

		// Setups components and listeners.
		actionListenerImpl = new ActionListenerImpl();

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.setPreferredSize(new Dimension(430, 300));  // Initial dummy dimension.

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		playButton = new JButton("PLAY");
		playButton.addActionListener(actionListenerImpl);

		// Setups vlcj objects.
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListenerImpl());

		// Layouts the components.
		controlPanel.add(playButton);

		add(mediaPlayerComponent, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);

		// Setups the frame.
		addWindowListener(new WindowListenerImpl());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void playVideo() {
		// Plays a sample video.
		// Note that playMedia is asynchronous, it returns almost instantly.
		mediaPlayerComponent.getMediaPlayer().playMedia("http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
	}

	private void packVideo(Dimension videoDimension) {
		// Packs the frame to the size appropriate to fit the video.
		mediaPlayerComponent.setPreferredSize(videoDimension);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}


	// Implementation of ActionListener for actions from components.
	private class ActionListenerImpl implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == playButton) {
				playVideo();
			}
		}
	}

	// Implementation of MediaPlayerEventListener for video events.
	// NOTE: media player events are dispatched in the context of a thread
	// that is NOT the AWT/Swing Event Dispatch Thread.
	private class MediaPlayerEventListenerImpl extends MediaPlayerEventAdapter {
		@Override
		public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
			final Dimension videoDimension = mediaPlayer.getVideoDimension();

			if (videoDimension != null) {
				// Uses invokeLater to execute packVideo in the context of the EDT.
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						packVideo(videoDimension);
					}
				});
			}
		}
	}

	// Implementation of WindowListener for the frame.
	private class WindowListenerImpl extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			if (mediaPlayerComponent != null) {
				mediaPlayerComponent.release(true);   // true = also releases media player factory.
			}
		}
	}
}