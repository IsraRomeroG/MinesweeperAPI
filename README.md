##The API documentation is on the following URL: https://api.israromero.com/swagger-ui.html

###Mines Generation Algorithm:
The mines generation algorithm is really simple, basically works with the following steps:

1- Get the number of cells multiplying the width by the height of the board.
2- Generates a random number into a range proportional to the number of cells
3- Take the random number generated and mark the corresponding cell in the board as a mine
4- Add 1 to the value to all cells around the new mine
5- Repeat until finishing placing all mines

###Reveal Cells Algorithm
The reveal cells algorithm is a little bit more complex, in this case I've done a recursive algorithm:

1- Open the requested cell
2- If the opened cell is a number or a mine then finish the algorithm.
3- If the opened cell is an empty cell then continue with the next step (4).
4- Open all adjacent cells
5- Validate if some adjacent cell is a **way\*** to continue exploring
6- If some adjacent cell is a **way\*** the algorithm return to the step 1 in a recursive form from this new position.

\**A way is a cell that is empty and has at least 1 hidden cell around it*

###Model:
- I decided to use the java.util.UUID to generate unique ids for each game
- A user can have many games
- A game has a board
- A board has an array of cells
- A cell has an id, a value and a boolean that indicates if the cell is hidden or not.


###UPLOADING:
To upload the proyect I used an EC2 instance from AWS.

In the beggining I tried to consume the API directly from the EC2 instance URL but I get an error from the browser due to the HTTPS protocol was required.

I needed to enable HTTPS and configure a valid certificate, in order to do this I used a Load Balancer and a certified given by AWS Certificate Manager.