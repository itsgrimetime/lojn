#include <stdio.h>
#include <allegro5/allegro.h>
#include <allegro5/allegro_image.h>
#include <allegro5/allegro_primitives.h>
#include <allegro5/allegro_font.h>
#include <allegro5/allegro_ttf.h>
#include <allegro5/allegro_audio.h>
#include <allegro5/allegro_acodec.h>
#include <iostream>


#include "Game.h"

float inputDelayCounter = 0.0;

int frames = 0;

Game::Game() {

	done = false;
	handleIn = false;
	renderImage = false;

	if(!al_init()) {
		fprintf(stderr, "failed to initialize allegro!\n");
	}

	display = al_create_display(WIDTH, HEIGHT);

	if(!display) {
		fprintf(stderr, "failed to create display!\n");
	}

	al_init_primitives_addon();
	al_init_image_addon();
	al_install_keyboard();
	al_install_mouse();
	al_init_font_addon();
	al_init_ttf_addon();

	Map* map = new Map("maps/map01.txt");

	Game::player = new Player(map, "images/fire_wizard.png");
	Game::camera = new Camera(map, player);

	for (int i = 0; i < (sizeof(keys) / sizeof(bool)); i++) { 
		keys[i] = false;
	}

	eventQueue = al_create_event_queue();
	
	font12 = al_load_font("fonts/arial.ttf", 12, 0);

	al_register_event_source(eventQueue, al_get_keyboard_event_source());
	al_register_event_source(eventQueue, al_get_mouse_event_source());
	
	inputDelayCounter = al_current_time();

	Render(); // Render the screen once before waiting for input

	while(!done) {
		al_wait_for_event(eventQueue, &ev);
		handleInput();
		if (al_is_event_queue_empty(eventQueue)) {
			Update();
		}
	}
		
	Destroy();

}

void Game::Destroy() {
	al_destroy_display(display);

}

void Game::handleInput() {
	if (ev.type == ALLEGRO_EVENT_KEY_DOWN) {
		switch (ev.keyboard.keycode) {
			case ALLEGRO_KEY_ESCAPE:
				done = true;
				break;
			case ALLEGRO_KEY_LEFT:
				keys[LEFT] = true;
				break;
			case ALLEGRO_KEY_RIGHT:
				keys[RIGHT] = true;
				break;
			case ALLEGRO_KEY_UP:
				keys[UP] = true;
				break;
			case ALLEGRO_KEY_DOWN:
				keys[DOWN] = true;
				break;
		}
	} else if (ev.type == ALLEGRO_EVENT_KEY_UP) {
		switch(ev.keyboard.keycode) {
		case ALLEGRO_KEY_LEFT:
			keys[LEFT] = false;
			break;
		case ALLEGRO_KEY_RIGHT:
			keys[RIGHT] = false;
			break;
		case ALLEGRO_KEY_UP:
			keys[UP] = false;
			break;
		case ALLEGRO_KEY_DOWN:
			keys[DOWN] = false;
			break;
		}
	}

}

void Game::Update() {
	if (al_current_time() - inputDelayCounter > INPUT_DELAY) {
		if (keys[RIGHT]) player->MoveRight();
		else if (keys[LEFT]) player->MoveLeft();
		else if (keys[UP]) player->MoveUp();
		else if (keys[DOWN]) player->MoveDown();
		Render();
	}
}

void Game::Render() {
	frames++;
	camera->Render();

	if (player->IsAlive())
		player->Render();
	
	if (debug)
		drawDebug();

	al_flip_display();
	al_clear_to_color(al_map_rgb(0, 0, 0));
}

void Game::drawDebug() {
	al_draw_filled_rectangle(3, 3, 120, 50, al_map_rgb(255, 255, 255));
	al_draw_textf(font12, al_map_rgb(0, 0, 0), 5, 5, 0, "Player x: %d, y: %d", player->GetX(), player->GetY());
	al_draw_textf(font12, al_map_rgb(0, 0, 0), 5, 20, 0, "Camera x: %d, y: %d", camera->getXTile(), camera->getYTile());
	al_draw_textf(font12, al_map_rgb(0, 0, 0), 5, 35, 0, "Frames: %d", frames);
}