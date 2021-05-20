public class NBody {

	public static double readRadius(String filePath) {
		In in = new In(filePath);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Planet[] readPlanets(String filePath) {
		In in = new In(filePath);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble(); 
		Planet[] planets = new Planet[numberOfPlanets];
		for(int i = 0; i < numberOfPlanets; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel,
			 yyVel, mass, imgFileName);
		}
		return planets;
	}

	public static void drawBackground(double radius) {
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(radius * -1, radius);
		StdDraw.picture(0, 0, imageToDraw);
	}

	public static void drawPlanets(Planet[] planets) {
		for(Planet p : planets) {
			p.draw();
		}
	}

	public static void draw(double T, double dt, String filename) {
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int waitTimeMilliseconds = 1;

		drawBackground(radius);
		drawPlanets(planets);

		StdDraw.enableDoubleBuffering();

		for(int i = 0; i < T; i += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int j = 0; j < planets.length; j++) {
				xForces[j] = planets[j].calcNetForceExertedByX(planets);
				yForces[j] = planets[j].calcNetForceExertedByY(planets);
			}
			for(int j = 0; j < planets.length; j++) {
				planets[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.clear();
			drawBackground(radius);
			drawPlanets(planets);
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
		}

		// after finishing drawing, print the current planets data
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		draw(T, dt, filename);
	}
}