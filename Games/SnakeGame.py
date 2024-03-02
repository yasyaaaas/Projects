import pygame
import random

# pygame setup
pygame.init()
square_width = 750
pixel_width = 50
screen = pygame.display.set_mode([square_width] * 2)
clock = pygame.time.Clock()
running = True

def generate_startig_position():
    position_range = (pixel_width // 2, square_width - pixel_width // 2, pixel_width)
    return [random.randrange(*position_range), random.randrange(*position_range)]

def reset():
    target.center = generate_startig_position()
    snake_pixel.center = generate_startig_position()
    return [snake_pixel.copy()]

def  is_out_of_bounds():
    return snake_pixel.bottom > square_width or snake_pixel.top < 0 or snake_pixel.left < 0 or snake_pixel.right > square_width
    
# snake
snake_pixel = pygame.rect.Rect(0, 0, pixel_width, pixel_width)
snake_pixel.center = generate_startig_position()
snake = [snake_pixel.copy()]
snake_direction = (0,0)
snake_lenght = 1;

# targuet
target = pygame.rect.Rect(0, 0, pixel_width - 2, pixel_width - 2)
target.center = generate_startig_position()

while running:
    # poll for events
    # pygame.QUIT event means the user clicked X to close your window
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # fill the screen with a color to wipe away anything from last frame
    screen.fill("black")

    # RENDER YOUR GAME HERE
    
    if  is_out_of_bounds():
        snake_length = 1
        target.center = generate_startig_position()
        snake_pixel.center = generate_startig_position()
        snake = [snake_pixel.copy()]
    
    if snake_pixel.center == target.center:
        target.center = generate_startig_position()
        snake_lenght += 1
        snake.append(snake_pixel.copy())
    
    keys = pygame.key.get_pressed()
    if keys[pygame.K_up]:
        snake_direction = (0, -pixel_width)
    if keys[pygame.K_DOWN]:
        snake_direction = (0, pixel_width)
    if keys[pygame.K_LEFT]:
        snake_direction = (-pixel_width, 0)
    if keys[pygame.K_RIGHT]:
        snake_direction = (pixel_width, 0)
    
    for snake_part in snake:
         pygame.draw.rect(screen, "green", snake_part)
         
    pygame.draw.rect(screen, "red", target)
    
    snake_pixel.move_ip(snake_direction)
    snake.append(snake_pixel.copy())
    snake = snake[-snake_lenght:]

    # flip() the display to put your work on screen
    pygame.display.flip()

    clock.tick(10)

pygame.quit()
