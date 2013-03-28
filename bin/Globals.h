#pragma once

const int WIDTH = 640;
const int HEIGHT = 480;

const int KEYS_IN_USE = 5; // Number of keys that the game handles input for

const int FPS = 60;
const int INPUT_DELAY = .1;

const bool debug = true;

enum ID { PLAYER, MONSTER };
enum STATE { TITLE, MENU, PLAYING, DEAD };