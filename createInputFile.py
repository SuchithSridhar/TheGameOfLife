#!/bin/python3
import pygame
import os

# Defining Constants for later use.
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)
WIDTH = 20
HEIGHT = 20
MARGIN = 5

def pygameWindow(rows, columns):
    grid = [[0 for _ in range(columns)] for _ in range(rows)]
    WINDOW_SIZE = [rows*HEIGHT+1 + rows*MARGIN, columns*WIDTH+1 + columns*MARGIN]

    pygame.init()
    screen = pygame.display.set_mode(WINDOW_SIZE)
    pygame.display.set_caption("Create input file")
    done = False
    clock = pygame.time.Clock()
    cellsClicked = []

    while not done:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                done = True
            elif event.type == pygame.MOUSEBUTTONDOWN:
                pos = pygame.mouse.get_pos()
                column = pos[0] // (WIDTH + MARGIN)
                row = pos[1] // (HEIGHT + MARGIN)
                grid[row][column] = 1
                if ([row, column] not in cellsClicked):
                    cellsClicked.append([row, column])
     
        screen.fill(BLACK)
     
        for row in range(rows):
            for column in range(columns):
                color = WHITE
                if grid[row][column] == 1:
                    color = GREEN
                pygame.draw.rect(screen,
                                 color,
                                 [(MARGIN + WIDTH) * column + MARGIN,
                                  (MARGIN + HEIGHT) * row + MARGIN,
                                  WIDTH,
                                  HEIGHT])
     
        clock.tick(60)
        pygame.display.flip()


    pygame.quit()
    return cellsClicked

def main():

    inputFile = input("Enter name of input file to write to: ")

    if os.path.exists(inputFile):
        print("File already exists.")
        i = input("Do you want to over write file (y/N): ")
        if i.lower() != "y":
           exit(0) 

    rows = int(input("Enter number of rows: "))
    columns = int(input("Enter number of columns: "))

    cellsClicked = pygameWindow(rows, columns)

    with open(inputFile, 'w') as f:
        f.write(f"{rows} {columns}\n")
        for line in cellsClicked:
            f.write(f"{line[0]} {line[1]}\n")


if __name__ == "__main__":
    main()
