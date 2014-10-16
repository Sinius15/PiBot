package com.sinius15.pibot.pc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sinius15.graph.Graph;

public class ControlFrame extends JFrame {

	private static final long serialVersionUID = -8344848980034299589L;

	private JPanel contentPane;

	public ControlFrame(Graph[] graphs, Setting... settings) {
		setResizable(false);

		setTitle("PiBot Controller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel rawDataPane = new JPanel();
		rawDataPane.setBorder(new TitledBorder(null, "Raw Data",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rawDataPane.setBounds(5, 5, 267, 529);
		contentPane.add(rawDataPane);
		GridBagLayout gbl_rawDataPane = new GridBagLayout();
		gbl_rawDataPane.rowWeights = new double[] {};
		gbl_rawDataPane.columnWeights = new double[] {};
		rawDataPane.setLayout(gbl_rawDataPane);

		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new TitledBorder(null, "Graphs",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		graphPanel.setBounds(282, 5, 622, 521);
		contentPane.add(graphPanel);
		graphPanel.setLayout(null);

		int y = 0;
		for (Setting set : settings) {
			GridBagConstraints gbc_lblRotationX = new GridBagConstraints();
			gbc_lblRotationX.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblRotationX.insets = new Insets(0, 0, 5, 5);
			gbc_lblRotationX.gridx = 0;
			gbc_lblRotationX.gridy = y;
			rawDataPane.add(set.label, gbc_lblRotationX);

			GridBagConstraints gbc_rotXField = new GridBagConstraints();
			gbc_rotXField.fill = GridBagConstraints.BOTH;
			gbc_rotXField.insets = new Insets(0, 0, 5, 0);
			gbc_rotXField.gridx = 1;
			gbc_rotXField.gridy = y;
			rawDataPane.add(set.field, gbc_rotXField);

			y++;
		}
		if (graphs[0] != null) {
			graphs[0].setBounds(14, 16, 295, 241);
			graphPanel.add(graphs[0]);
		}

		if (graphs[1] != null) {
			graphs[1].setBounds(315, 16, 295, 241);
			graphPanel.add(graphs[1]);
		}
		if (graphs[2] != null) {
			graphs[2].setBounds(14, 268, 295, 241);
			graphPanel.add(graphs[2]);
		}
		if (graphs[3] != null) {
			graphs[3].setBounds(315, 268, 295, 241);
			graphPanel.add(graphs[3]);
		}
	}
}
