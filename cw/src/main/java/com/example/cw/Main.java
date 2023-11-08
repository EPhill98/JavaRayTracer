package com.example.cw;/*
CS-255 Getting started code for the assignment
I do not give you permission to post this code online
Do not post your solution online
Do not copy code
Do not use JavaFX functions or other libraries to do the main parts of the assignment (i.e. ray tracing steps 1-7)
All of those functions must be written by yourself
You may use libraries to achieve a better GUI
*/

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @FXML
    private ImageView imageView;
    @FXML
    private Slider rCol = new Slider(0, 255, 0);
    @FXML
    private Slider gCol = new Slider(0, 255, 0);
    @FXML
    private Slider bCol = new Slider(0, 255, 0);
    @FXML
    private Slider myLightX = new Slider(-500, 500, 0);
    @FXML
    private Slider myLightY = new Slider(-500, 500, 0);
    @FXML
    private Slider myLightZ = new Slider(-500, 500, 0);
    @FXML
    private Slider posXSlide = new Slider(-500, 500, 0);
    @FXML
    private Slider posYSlide = new Slider(-500, 500, 0);
    @FXML
    private Slider posZSlide = new Slider(-500, 500, 0);
    @FXML
    int xSlide = 0;
    @FXML
    int ySlide = 0;
    @FXML
    int zSlide = 0;
    @FXML
    int lightX = 0;
    @FXML
    int lightY = 0;
    @FXML
    int lightZ = 0;
    @FXML
    Vector newCol;
    @FXML
    private TextField mySphere = new TextField();
    @FXML
    private Slider myX = new Slider(-640, 640, 0);
    @FXML
    private Slider myY = new Slider(-640, 640, 0);
    @FXML
    private Slider myZ = new Slider(-640, 640, 0);
    int newPosZ;
    int newPosY;
    int newPosX;
    int Width = 640;
    int Height = 640;
    String input = "1";
    ArrayList<Sphere> lst = new ArrayList<Sphere>();
    Vector sphereCol1 = new Vector(0, 0, 1);
    Vector sphereCol2 = new Vector(1, 0, 0);
    Vector sphereCol3 = new Vector(0, 1, 0);
    Vector sphereCol4 = new Vector(0.5, 0.5, 1);

    Sphere s1 = new Sphere(sphereCol1, 0, 200, -100, 50);
    Sphere s2 = new Sphere(sphereCol2, -100, -50, 0, 75);
    Sphere s3 = new Sphere(sphereCol3, 200, 100, 0, 60);
    Sphere s4 = new Sphere(sphereCol4, 400, 200, 200, 150);
    Sphere newSphere = s1;

    {
        lst.add(s1);
        lst.add(s2);
        lst.add(s3);
        lst.add(s4);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent groot = FXMLLoader.load(getClass().getResource("app.fxml"));
        stage.setTitle("Ray Tracing");
        Scene scene = new Scene(groot, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }


    public void Render(WritableImage image) {
        int w = (int) image.getWidth(), h = (int) image.getHeight();
        PixelWriter image_writer = image.getPixelWriter();
        Vector bkgCol = new Vector(0.5, 0.5, 0.5);
        Vector light = new Vector(lightX, lightY, lightZ);

        Render rend = new Render(lst, image_writer, w, h);
        rend.setxSlide(xSlide);
        rend.setySlide(ySlide);
        rend.setzSlide(zSlide);

        rend.setBkgCol(bkgCol);
        rend.setLight(light);
        rend.drawImage();
    }


    public static void main(String[] args) {
        launch();
    }

    @FXML
    public void render() {
        WritableImage image = new WritableImage(Width, Height);
        Render(image);
        imageView.setFitWidth(Width);
        imageView.setFitHeight(Height);
        imageView.setImage(image);

    }

    @FXML
    public void changeValueR() {
        rCol.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                newCol.setX(newValue.intValue() / 255.0);
                render();
            }
        });
    }

    public void changeValueG() {
        gCol.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                newCol.setY(newValue.intValue() / 255.0);
                render();
            }
        });
    }

    public void changeValueB() {
        bCol.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                newCol.setZ(newValue.intValue() / 255.0);
                render();
            }
        });
    }

    public void changeXCam() {
        myX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                xSlide = newValue.intValue();
                render();
            }
        });
    }

    public void changeYslide() {
        myY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                ySlide = newValue.intValue();
                render();
            }
        });
    }

    public void changeZSlide() {
        myZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                zSlide = newValue.intValue();
                render();
            }
        });
    }

    public void changeLightX() {
        myLightX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                lightX = newValue.intValue();
                render();
            }
        });
    }

    public void changeLightY() {
        myLightY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                lightY = newValue.intValue();
                render();
            }
        });
    }

    public void changeLightZ() {
        myLightZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                lightZ = newValue.intValue();
                render();
            }
        });
    }


    public void changePosX(MouseEvent mouseEvent) {
        posXSlide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                newPosX = newValue.intValue();
                newSphere.setX(newPosX);
                newSphere.setCenterSpehere();
                render();
            }
        });
    }


    public void changePosY(MouseEvent mouseEvent) {
        posYSlide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                newPosY = newValue.intValue();
                newSphere.setY(newPosY);
                newSphere.setCenterSpehere();
                render();
            }
        });
    }

    public void changePosZ(MouseEvent mouseEvent) {
        posZSlide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                newPosZ = newValue.intValue();
                newSphere.setZ(newPosZ);
                newSphere.setCenterSpehere();
                render();
            }
        });
    }

    public void changeSphere() {
        input = mySphere.getText();
        switch (input) {
            case "0":
                newSphere = s1;
                newCol = s1.getSphereCol();
                break;
            case "1":
                newSphere = s2;
                newCol = s2.getSphereCol();
                break;
            case "2":
                newSphere = s3;
                newCol = s3.getSphereCol();
                break;
            case "3":
                newSphere = s4;
                newCol = s4.getSphereCol();
                break;
        }
    }
}