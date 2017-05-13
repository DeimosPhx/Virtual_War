package joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Terrain.*;
import unite.*;

public class LancementEnFX extends Application {
	private int tailleX =10,tailleY=10, tauxObstacle=0;
	private boolean joueur1EstHumain = true, joueur2EstHumain = true;
	private int nbCharJ1, nbCharJ2, nbTireurJ1, nbTireurJ2, nbPiegeurJ1, nbPiegeurJ2;
	private ArrayList<Robot> compoJ1 =  new ArrayList<Robot>();
	private ArrayList<Robot> compoJ2 = new ArrayList<Robot>();
	
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();

		HBox ObstacleBox = new HBox();

		HBox J1hbox1 = new HBox();
		HBox J1hbox2 = new HBox();
		HBox J1hbox3 = new HBox();
		HBox J2hbox1 = new HBox();
		HBox J2hbox2 = new HBox();
		HBox J2hbox3 = new HBox();

		Canvas canvas = new Canvas (0,80);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		/*Plateau plat = Menu.acceuil();
		Vue vueJ1 = new Vue(1,plat);
		Vue vueJ2 = new Vue(2,plat);
		Base B1 = new Base(new Coordonnees(0,0),1);
		Base B2 = new Base(new Coordonnees(9,9),2);
		Joueur J1 = new Joueur(1,B1,vueJ1);
		Joueur J2 = new Joueur(2,B2,vueJ2);	*/
		Label obstacleIndicationLabel = new Label("Taux d'obstacle : ");
		Label tauxObstacleLabel = new Label("0");
		Label compoJ1 = new Label("Joueur 1");
		Label compoJ2 = new Label("Joueur 2");

		Slider slider = new Slider(0,70,1);

		Button J1CharPlus = 	new Button(" + ");
		Button J1PiegeurPlus = new Button(" + ");
		Button J1TireurPlus = 	new Button(" + ");

		Button J1CharMoins = 	new Button(" - ");
		Button J1PiegeurMoins = new Button(" - ");
		Button J1TireurMoins = 	new Button(" - ");

		Button J2CharMoins = 	new Button(" - ");
		Button J2PiegeurMoins = new Button(" - ");
		Button J2TireurMoins = 	new Button(" - ");

		Button J2CharPlus = 	new Button(" + ");
		Button J2PiegeurPlus = 	new Button(" + ");
		Button J2TireurPlus = 	new Button(" + ");

		Label J1Char 	= new Label("0");
		Label J1Piegeur = new Label("0");
		Label J1Tireur 	= new Label("0");

		Label J2Char 	= new Label("0");
		Label J2Piegeur = new Label("0");
		Label J2Tireur 	= new Label("0");

		J1CharPlus.setOnAction(e ->{
			if(nbCharJ1<9){
				nbCharJ1++;
				J1Char.setText(nbCharJ1+"");
			}
		});
		J1PiegeurPlus.setOnAction(e ->{
			if(nbPiegeurJ1<9){
				nbPiegeurJ1++;
				J1Piegeur.setText(nbPiegeurJ1+"");
			}
		});
		J1TireurPlus.setOnAction(e ->{
			if(nbTireurJ1<9){
				nbTireurJ1++;
				J1Tireur.setText(nbTireurJ1+"");
			}
		});
		J2CharPlus.setOnAction(e ->{
			if(nbCharJ2<9){
				nbCharJ2++;
				J2Char.setText(nbCharJ2+"");
			}
		});
		J2PiegeurPlus.setOnAction(e ->{
			if(nbPiegeurJ2<9){
				nbPiegeurJ2++;
				J2Piegeur.setText(nbPiegeurJ2+"");
			}
		});
		J2TireurPlus.setOnAction(e ->{
			if(nbTireurJ2<9){
				nbTireurJ2++;
				J2Tireur.setText(nbTireurJ2+"");
			}
		});



		J1CharMoins.setOnAction(e ->{
			if(nbCharJ1>0){
				nbCharJ1--;
				J1Char.setText(nbCharJ1+"");
			}
		});
		J1PiegeurMoins.setOnAction(e ->{
			if(nbPiegeurJ1>0){
				nbPiegeurJ1--;
				J1Piegeur.setText(nbPiegeurJ1+"");
			}
		});
		J1TireurMoins.setOnAction(e ->{
			if(nbTireurJ1>0){
				nbTireurJ1--;
				J1Tireur.setText(nbTireurJ1+"");
			}
		});
		J2CharMoins.setOnAction(e ->{
			if(nbCharJ2>0){
				nbCharJ2--;
				J2Char.setText(nbCharJ2+"");
			}
		});
		J2PiegeurMoins.setOnAction(e ->{
			if(nbPiegeurJ2>0){
				nbPiegeurJ2--;
				J2Piegeur.setText(nbPiegeurJ2+"");
			}
		});
		J2TireurMoins.setOnAction(e ->{
			if(nbTireurJ2>0){
				nbTireurJ2--;
				J2Tireur.setText(nbTireurJ2+"");
			}
		});

		final ToggleGroup groupJ1 = new ToggleGroup();

		Label identiteJ1 = new Label("Identité du Joueur 1 : ");
		HBox humainIaJ1 = new HBox();
		RadioButton AiJ1 = new RadioButton("IA");
		AiJ1.setToggleGroup(groupJ1);
		RadioButton humainJ1 = new RadioButton("Humain");
		humainJ1.setToggleGroup(groupJ1);
		humainJ1.setSelected(true);

		humainIaJ1.getChildren().addAll(AiJ1,humainJ1);


		final ToggleGroup groupJ2 = new ToggleGroup();

		Label identiteJ2 = new Label("Identité du Joueur 2 : ");
		HBox humainIaJ2 = new HBox();
		RadioButton AiJ2 = new RadioButton("IA");
		AiJ2.setToggleGroup(groupJ2);
		RadioButton humainJ2 = new RadioButton("Humain");
		humainJ2.setToggleGroup(groupJ2);
		humainJ2.setSelected(true);

		humainIaJ2.getChildren().addAll(AiJ2,humainJ2);


		Button start = new Button("Sutaruto !");
		start.setMinSize(40, 20);

		ImageView Ichar 	= new ImageView(new Image("char.png"));
		ImageView Ipiegeur 	= new ImageView(new Image("piegeur.png"));
		ImageView Itireur 	= new ImageView(new Image("tireur.png"));
		ImageView Mchar 	= new ImageView(new Image("char.png"));
		ImageView Mpiegeur 	= new ImageView(new Image("piegeur.png"));
		ImageView Mtireur 	= new ImageView(new Image("tireur.png"));

		ObstacleBox.getChildren().addAll(obstacleIndicationLabel,tauxObstacleLabel);

		J1hbox1.getChildren().addAll(Ichar,Ipiegeur,Itireur);
		J2hbox1.getChildren().addAll(Mchar,Mpiegeur,Mtireur);


		J1hbox2.getChildren().addAll(J1CharMoins,J1CharPlus,J1PiegeurMoins,J1PiegeurPlus,J1TireurMoins,J1TireurPlus);
		J2hbox2.getChildren().addAll(J2CharMoins,J2CharPlus,J2PiegeurMoins,J2PiegeurPlus,J2TireurMoins,J2TireurPlus);

		J1hbox3.getChildren().addAll(J1Char,J1Piegeur,J1Tireur);
		J2hbox3.getChildren().addAll(J2Char,J2Piegeur,J2Tireur);

		slider.setOnMouseDragged(e->{
			tauxObstacle = (int)(slider.getValue());
			tauxObstacleLabel.setText(""+tauxObstacle);
		});

		/*
		plat.setBase(B1);
		plat.setBase(B2);
		Menu.setObstacle(plat,J1,J2);
		J1.setList(Menu.compo(J1, plat));
		J2.setList(Menu.compo(J2, plat));
		ArrayList<Robot> compoj1 = new ArrayList<Robot>();
		for(Robot r : J1.getListeRobot()){
			compoj1.add(r);
		}
		ArrayList<Robot> compoj2 = new ArrayList<Robot>();
		for(Robot r : J2.getListeRobot()){
			compoj2.add(r);
		}
		B1.setList(compoj1);
		B2.setList(compoj2);
		 */


		root.getChildren().addAll(ObstacleBox,slider,compoJ1,J1hbox1,J1hbox3,J1hbox2,compoJ2,J2hbox1,J2hbox3,J2hbox2,identiteJ1,humainIaJ1,identiteJ2,humainIaJ2,start);
		VBox.setMargin(ObstacleBox, new Insets(10, 0, 10, 70));

		VBox.setMargin(J1hbox1, new Insets(0, 0, 0, 8));
		VBox.setMargin(J2hbox1, new Insets(0, 0, 0, 8));

		VBox.setMargin(J1hbox2, new Insets(0, 10, 0, 10));
		VBox.setMargin(J2hbox2, new Insets(0, 10, 0, 10));
		VBox.setMargin(compoJ1, new Insets(10, 0, 0, 100));
		VBox.setMargin(compoJ2, new Insets(10, 0, 0, 100));


		HBox.setMargin(J1CharPlus, 		new Insets(0,10,0,0));
		HBox.setMargin(J1PiegeurPlus, 	new Insets(0,10,0,0));
		HBox.setMargin(J2CharPlus, 		new Insets(0,10,0,0));
		HBox.setMargin(J2PiegeurPlus, 	new Insets(0,10,0,0));

		HBox.setMargin(J1Char,		new Insets(0, 70, 0, 40));
		HBox.setMargin(J1Piegeur,	new Insets(0, 70, 0, 0));


		HBox.setMargin(J2Char,		new Insets(0, 70, 0, 40));
		HBox.setMargin(J2Piegeur,	new Insets(0, 70, 0, 0));

		VBox.setMargin(identiteJ1, new Insets(30, 0, 10, 50));
		VBox.setMargin(identiteJ2, new Insets(10, 0, 10, 50));

		VBox.setMargin(humainIaJ1, new Insets(30, 0, 10, 50));
		VBox.setMargin(humainIaJ2, new Insets(30, 0, 0, 50));

		HBox.setMargin(AiJ1, new Insets(0, 30, 0, 0));
		HBox.setMargin(AiJ2, new Insets(0, 30, 0, 0));

		VBox.setMargin(start, new Insets(20,0,0,80));
		stage.setResizable(false);

		//new Insets(topRightBottomLeft)

		root.setBackground(new Background(new BackgroundImage(new Image("background.png"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setTitle("Virtual War");
		stage.setScene(scene);
		stage.show();		

		start.setOnAction(e->{
			joueur1EstHumain = humainJ1.isSelected();
			joueur2EstHumain = humainJ2.isSelected();
			Jeu jeu =new Jeu();
			jeu.setReglage(tauxObstacle,joueur1EstHumain, joueur2EstHumain,nbCharJ1, nbCharJ2, nbTireurJ1, nbTireurJ2, nbPiegeurJ1, nbPiegeurJ2);
			try {
				jeu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
