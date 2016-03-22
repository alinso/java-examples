/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
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
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MinimalPlayerWithPlayFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private ActionListenerImpl actionListenerImpl;

	private JPanel mainPanel;
	private JPanel controlPanel;
	private Canvas videoSurface;
	private JButton playButton;

	private MediaPlayerFactory mediaPlayerFactory;
	private EmbeddedMediaPlayer mediaPlayer;

	public MinimalPlayerWithPlayFrame() {
		super("Minimal Player With Play - Java Example by andbin");

		actionListenerImpl = new ActionListenerImpl();

		mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));

		videoSurface = new Canvas();
		videoSurface.setBackground(Color.BLACK);
		videoSurface.setPreferredSize(new Dimension(400, 300));  // Initial dummy dimension.

		playButton = new JButton("PLAY");
		playButton.addActionListener(actionListenerImpl);

		String[] vlcArgs = {
			"--quiet",            // Avoids warning and information messages.
			"--quiet-synchro",    // Avoids debug infos about video output synchronization.
			"--intf", "dummy",    // No interface.
		};

		mediaPlayerFactory = new MediaPlayerFactory(vlcArgs);
		mediaPlayerFactory.setUserAgent("Minimal Player by andbin");

		mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
		mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
		mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventListenerImpl());

		// Layouts the components.
		controlPanel.add(playButton);

		mainPanel.add(videoSurface, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);   // Sets mainPanel as the new content pane.

		// Setups the frame.
		addWindowListener(new WindowListenerImpl());

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void playVideo() {
		// Plays a sample video.
		mediaPlayer.prepareMedia("http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
		mediaPlayer.play();
	}

	private void packVideo(Dimension videoDimension) {
		// Packs the frame to the size appropriate to fit the video.
		videoSurface.setPreferredSize(videoDimension);
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
	private class MediaPlayerEventListenerImpl extends MediaPlayerEventAdapter {
		@Override
		public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
			final Dimension videoDimension = mediaPlayer.getVideoDimension();

			if (videoDimension != null) {
				// Uses invokeLater because media player events are dispatched in
				// the context of a thread that is NOT the Event Dispatch Thread.
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
			if (mediaPlayer != null) {
				mediaPlayer.release();
				mediaPlayer = null;
			}

			if (mediaPlayerFactory != null) {
				mediaPlayerFactory.release();
				mediaPlayerFactory = null;
			}
		}
	}
}