#pragma once

#include "Map.h"
#include "Player.h"
#include "Camera.h"
#include <allegro5\allegro_font.h>

class Game {
protected:
	ALLEGRO_DISPLAY *display;
	ALLEGRO_FONT *font12;
	ALLEGRO_EVENT ev;
	ALLEGRO_TIMER* timer;
	ALLEGRO_EVENT_QUEUE* eventQueue;
	Player* player;
	Camera* camera;
	bool keys[KEYS_IN_USE]; // fuckin shit yeah
	bool done;
	bool handleIn;
	bool renderImage;
	float gameTime;
	static enum KEYS { LEFT, RIGHT, UP, DOWN, SPACE };

public:
	Game();
	void Update();
	void Destroy();
	void Init();
	void Render();
	void handleInput();
	void drawDebug();
};