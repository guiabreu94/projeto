package com.lycha.example.augmentedreality;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.location.Location;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by krzysztofjackowski on 24/09/15.
 */
public class CameraViewActivity extends Activity implements
		SurfaceHolder.Callback, OnLocationChangedListener, OnAzimuthChangedListener{

	private Camera mCamera;
	private SurfaceHolder mSurfaceHolder;
	private boolean isCameraviewOn = false;
	private AugmentedPOI mPoi;

	private double mAzimuthReal = 0;
	private double mAzimuthTeoretical = 0;
	private static double AZIMUTH_ACCURACY = 20;
	private double mMyLatitude = 0;
	private double mMyLongitude = 0;

	private MyCurrentAzimuth myCurrentAzimuth;
	private MyCurrentLocation myCurrentLocation;

	TextView descriptionTextView;
	ImageView pointerIcon;

	Canvas canvas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_view);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setupListeners();
		setupLayout();
		setAugmentedRealityPoint();
       double lat[] ={-22.976794738565, -22.976068729789, -22.976779922011, -22.980997610156, -22.981540862521, -22.977012046533, -22.976740411513, -22.981422334921, -22.98059757747, -22.979007312391, -22.979866650486, -22.977012046492, -22.981422334879, -22.980671657674, -22.980350643148, -22.979150535782, -22.982059419556, -22.979194984375, -22.981338278124, -22.976883637319, -22.981279014248, -22977431724783.0, -22.977417028574, -22.979160413228, -22.981649512726, -22.97782694823, -22.976804616152, -22.977026862958, -22.978059070918, -22.977273803374, -22.979017189834, -22.977718294998, -22.977392334561, -22.978903598826, -22.980938346068, -22.979199823505, -22.981086506156, -22.97717986643, -22.978987557453, -22.977105883877, -22.979012251075, -22.979160413198, -22.977071312263, -22.976962658379, -22.977244170534, -22.976799677362, -22.976804616122, -22.978271437988, -22.978281315604, -22.978370213299, -22.978281315523, -22.978419600928, -22.978202295308, -22.978261560534, -22.978399845879, -22.978380090827, -22.978355397007, -22.978118336194, -22.978177601376, -22.978222050305, -22.978192417768, -22.978449233497, -22.977901030009, -22.977782499213, -22.97794054025, -22.97785658098, -22.981728530996, -22.978083764771, -22.980548190634, -22.979264126649, -22.977016985316, -22.97779731557, -22.980711167093, -22.97892335378, -22.978147968838, -22.981881628746, -22.980202482323, -22.978844333869, -22.981086506141, -22.981130854443, -22.981313684523, -22.980869204731, -22.98137778736, -22.981219750345, -22.981437051193, -22.981689021875, -22.981323561838, -22.981279014247, -22.981318523501, -22.98069141239, -22.980992571809, -22.978913476309, -22.978913476315, -22.978923353802, -22.978873966353, -22.978923353802, -22.978923353802, -22.978893721331, -22.979022128627, -22.978799785459};
        double lon[] = {-43.394844708441, -43.393010077371, -43.394780335321, -43.395922956456, -43.394796428671, -43.396014151572, -43.398428139669, -43.396298465723, -43.397178230269, -43.395772752729, -43.397264060927, -43.398438868471, -43.395542082732, -43.397033390968, -43.397349891662, -43.397473273247, -43.39476960659, -43.397784409463, -43.395604049516, -43.398497877112, -43.395695244622, -4339248228071.0, -43.397344527183, -43.397618112494, -43.395965871805, -43.394844708367, -43.398417410772, -43.398347673405, -43.398932394981, -43.397725400905, -43.395413336669, -43.394442377082, -43.397451815519, -43.396035609245, -43.396813449772, -43.397964393494, -43.396588144299, -43.397966799724, -43.395279226303, -43.398159918693, -43.395842490104, -43.396448669339, -43.398240385033, -43.395595726958, -43.397108492819, -43.397762951821, -43.394640860459, -43.394469199074, -43.394329724299, -43.394431648148, -43.394174156082, -43.394378003968, -43.394415554988, -43.394243893611, -43.394292173279, -43.394206342591, -43.39412587632, -43.394200978267, -43.394313630951, -43.394378003968, -43.39413660525, -43.394431648148, -43.394479927999, -43.394431648235, -43.394442377068, -43.394372639647, -43.395257768605, -43.394538936587, -43.396405754056, -43.398283300363, -43.39515584465, -43.39547770976, -43.396765170065, -43.39582103251, -43.396185812948, -43.395804939227, -43.396464762689, -43.398567614543, -43.396454033838, -43.396446263145, -43.396078524576, -43.396754441244, -43.396135126901, -43.396338974785, -43.396049296212, -43.395906863202, -43.396389660824, -43.396274601768, -43.396199499916, -43.396969017952, -43.396577531149, -43.394780335416, -43.394994912147, -43.395059285164, -43.395370421404, -43.394866166115, -43.394941267967, -43.395134387012, -43.394635496124, -43.39526861314};
        String nome[] = {"Batata no Cone", "Beerstation Heineken ", "Big Daddy's", "Boteco do Amaral", "Famiglia Rivitti", "Ragazzo", "Ahead Light Stage", "Casa Fanta Guaraná", "Chapel", "Chilli Beans", "Coca-Cola ", "Colgate", "Detran", "DHL", "Digital Stage", "Doritos", "EDM Stage", "Escape Multishow", "Facebook", "Fiever", "Flicks", "Game XP", "GOL/DELTA/AF KLM", "Heineken ", "Heineken - Rock & Recycle", "Itaú’s Giant Headphone", "Johnnie Walker", "Leader", "Main Stage", "Maybelline", "Movida", "Movida Lounge", "Niely", "Nissin", "Prefeitura de Manaus", "Prefeitura do RJ", "Rock District", "Rock in Rio Club", "Rock Street", "Salamitos", "Sancta Maggiore", "Sky", "Submarino", "Sunset ", "Tinder", "VIP Area", "Visa Tattoo Pay", "Açougue Vegano", "Botero", "Deli Delícia", "Di Blasi", "Famiglia Rivitti", "Feijú do Benola ", "Gouranga Veggie", "Karaage - Chicken Gourmet ", "O Melhor Pastel do Mundo", "Ogro Jimmy", "On Japa", "Pedro Benoliel", "Roberta Sudbrack", "Sertanorte", "Sol", "Boteco do Amaral ", "Domino's", "Forno de Minas ", "Lounge", "Sorvete Habib's - Eletronica", "Sorvete Habib's - Gourmet Area", "Sorvete Habib's - Roller Coaster", "Sorvete Habib's - Sponsors Area", "Sorvete Habib's - Sunset", "Main Store", "Rock District Store", "Rock Street Store", "Ferris Wheel", "Mega Drop", "Roller Coaster", "Zip Line", "Batata no Cone", "Casa do Alemão ", "Domino’s", "Espetto Carioca ", "Expresso da Tapioca ", "Fornalha ", "La Furgoneta ", "Loucos por Churros", "Natural d'Petrópolis", "Ornelas ", "Pastel Carioca ", "Prezunic ", "Rock District", "Batata no Cone", "Benkei", "Boteco do Amaral", "Cup Noodles", "Domino's ", "Geneal ", "Las Empanadas", "Ragazzo ", "Rock Street África"};
		String tipo[] = {"Bars", "Bars", "Bars", "Bars", "Bars", "Bars", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Experiences", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Gourmet Sq", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Lounge ", "Merchandise", "Merchandise", "Merchandise", "Rides", "Rides", "Rides", "Rides", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock Dist", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr", "Rock St. Afr"};

		AugmentedPOI[] pontos = new AugmentedPOI[lat.length];
		pontos = initpontos(lat,lon,nome,tipo);
		int tam;



	}



	/*public class Pontos implements Serializable {
		//super.onDraw(canvas);
		public Double lat;
		public Double lon;
		public String nome;
		public String info;



		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawCircle(x, y, radius, paint);
			canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
			final int radius = 30;
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(Color.WHITE);
			paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
			paint.setTextSize(60);


		}*/
		/*canvas.drawCircle(x, y, radius, paint);
                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
		final int radius = 30;
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);*/
	//}

	public AugmentedPOI[] initpontos(double lat[],double lon[],String nome[],String tipo[]){
	int cont;
		AugmentedPOI[] pontos = new AugmentedPOI[lat.length];
		for(cont=0; cont < lat.length;cont++){

			mPoi = new AugmentedPOI(
					nome[cont],
					tipo[cont],
					lat[cont],
					lon[cont]
			);
			pontos[cont]=mPoi;
		}
		return pontos;
	}
	private void setAugmentedRealityPoint() {
		mPoi = new AugmentedPOI(
				"Teste",
				"Testando a funcionalidade",
				-23.00980000,
				-43.43700000
		);
	}

	public double calculateTeoreticalAzimuth() {
		double dX = mPoi.getPoiLatitude() - mMyLatitude;
		double dY = mPoi.getPoiLongitude() - mMyLongitude;

		double phiAngle;
		double tanPhi;
		double azimuth = 0;

		tanPhi = Math.abs(dY / dX);
		phiAngle = Math.atan(tanPhi);
		phiAngle = Math.toDegrees(phiAngle);
		//Toast.makeText(this,"phiAngle"+phiAngle, Toast.LENGTH_LONG).show();

		if (dX > 0 && dY > 0) { // I quater
			return azimuth = phiAngle;
		} else if (dX < 0 && dY > 0) { // II
			return azimuth = 180 - phiAngle;
		} else if (dX < 0 && dY < 0) { // III
			return azimuth = 180 + phiAngle;
		} else if (dX > 0 && dY < 0) { // IV
			return azimuth = 360 - phiAngle;
		}

		return phiAngle;
	}
	
	private List<Double> calculateAzimuthAccuracy(double azimuth) {
		double minAngle = azimuth - AZIMUTH_ACCURACY;
		double maxAngle = azimuth + AZIMUTH_ACCURACY;
		List<Double> minMax = new ArrayList<Double>();

		if (minAngle < 0)
			minAngle += 360;

		if (maxAngle >= 360)
			maxAngle -= 360;

		minMax.clear();
		minMax.add(minAngle);
		minMax.add(maxAngle);

		return minMax;
	}

	private boolean isBetween(double minAngle, double maxAngle, double azimuth) {
		if (minAngle > maxAngle) {
			if (isBetween(0, maxAngle, azimuth) && isBetween(minAngle, 360, azimuth))
				return true;
		} else {
			if (azimuth > minAngle && azimuth < maxAngle)
				return true;
		}
		return false;
	}

	private void updateDescription() {
		descriptionTextView.setText(mPoi.getPoiName() + " azimuthTeoretical "
				+ mAzimuthTeoretical + " azimuthReal " + mAzimuthReal + " latitude "
				+ mMyLatitude + " longitude " + mMyLongitude + "minAngle" );
	}

	@Override
	public void onLocationChanged(Location location) {
		mMyLatitude = location.getLatitude();
		mMyLongitude = location.getLongitude();
		mAzimuthTeoretical = calculateTeoreticalAzimuth();
		Toast.makeText(this,"latitude: "+location.getLatitude()+" longitude: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
		updateDescription();
	}

	@Override
	public void onAzimuthChanged(float azimuthChangedFrom, float azimuthChangedTo) {
		mAzimuthReal = azimuthChangedTo;
		mAzimuthTeoretical = calculateTeoreticalAzimuth();

		pointerIcon = (ImageView) findViewById(R.id.icon);

		double minAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(0);
		double maxAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(1);

		Toast.makeText(this,"minAngle"+minAngle+" maxAngle"+maxAngle+" mAzimuthReal"+mAzimuthReal, Toast.LENGTH_LONG).show();

		if (isBetween(minAngle, maxAngle, mAzimuthReal)) {
			pointerIcon.setVisibility(View.VISIBLE);
		} else {
			pointerIcon.setVisibility(View.INVISIBLE);
		}

		updateDescription();
	}

	@Override
	protected void onStop() {
		myCurrentAzimuth.stop();
		myCurrentLocation.stop();
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		myCurrentAzimuth.start();
		myCurrentLocation.start();
	}

	private void setupListeners() {
		myCurrentLocation = new MyCurrentLocation(this);
		myCurrentLocation.buildGoogleApiClient(this);
		myCurrentLocation.start();

		myCurrentAzimuth = new MyCurrentAzimuth(this, this);
		myCurrentAzimuth.start();
	}

	private void setupLayout() {
		descriptionTextView = (TextView) findViewById(R.id.cameraTextView);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.cameraview);
		mSurfaceHolder = surfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
							   int height) {
		if (isCameraviewOn) {
			mCamera.stopPreview();
			isCameraviewOn = false;
		}

		if (mCamera != null) {
			try {
				mCamera.setPreviewDisplay(mSurfaceHolder);
				mCamera.startPreview();
				isCameraviewOn = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		mCamera.setDisplayOrientation(90);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
		isCameraviewOn = false;
	}

}
