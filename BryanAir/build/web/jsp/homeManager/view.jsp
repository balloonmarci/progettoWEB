<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file="/include/head.jspf"%>
    <body>
      <%@include file="/include/header.jspf"%>
      <section class="m-centrale">
        <div class="m-aereoimg">
        </div>
        <div class="main-form">
          <form class="form-color">
            <input type="radio" id="Andata" name="viaggio">
            <label for="Andata"> Sola andata </label>
            <input type="radio" id="AndataRitorno" name="viaggio">
            <label for="AndataRitorno"> Andata e ritorno </label> </br></br>

            <input type="text" placeholder="Aeroporto di partenza" required>
            <input type="text" placeholder="Aeroporto di destinazione" required></br></br>
            <input type="date" name="DataAndata" required>
            <input type="date" name="DataRitorno" required></br></br>

            <!--<label for="number"> Numero passeggeri</label>
            <input id="number" type="number" value="1"></br></br>-->

            <input type="submit" value="Cerca" class="submit-dimensioni submit-color">

          </form>
        </div>
      </section>
      <div>
      </div>
      <main class = "m-main clearfix">
        <div class="m-content clearfix">
          <div>
            <article>
              <h1>Caraibi</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
            <article>
              <h1>Maldive</h1>
              <img src="images/maldive.png" alt="Maldive">
            </article>
            <article>
              <h1>New York</h1>
              <img src="images/nycity.png" alt="New York">
            </article>
          </div>
          <div>
            <article>
              <h1>Roma</h1>
              <img src="images/roma.png" alt="Roma">
            </article>
            <article>
              <h1>Toronto</h1>
              <img src="images/toronto.png" alt="Toronto">
            </article>
            <article>
              <h1>Caraibi</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
          </div>
          <div>
            <article>
              <h1>Mosca</h1>
              <img src="images/cremlino.png" alt="Cremlino">
            </article>
            <article>
              <h1>Luogo 8</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
            <article>
              <h1>Luogo 9</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
          </div>
        </div>
        <aside class="m-sidebar">
            <div>
                <h3>See your Wishlist!</h3>
                <article></article>
                <article></article>
                <article></article>
            </div>
        </aside>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
</html>
