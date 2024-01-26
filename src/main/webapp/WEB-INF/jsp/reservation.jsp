<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
      <link rel="stylesheet" href="css/style.css">
 
 
  <script type="text/javascript">
        // Function to generate time slots from start to end with a given interval
        function generateTimeSlots(start, end, interval) {
            var timeSlots = [];
            var currentTime = new Date('1970-01-01T' + start + 'Z');
            var endTime = new Date('1970-01-01T' + end + 'Z');

            while (currentTime <= endTime) {
                var timeSlot = currentTime.toTimeString().substring(0, 5);
                timeSlots.push(timeSlot);
                currentTime.setMinutes(currentTime.getMinutes() + interval);
            }

            return timeSlots;
        }

        // Generate time slots for the specified periods
        var timeSlotsMorning = generateTimeSlots('11:00', '14:30', 30);
        var timeSlotsEvening = generateTimeSlots('19:00', '22:30', 30);

        // Populate the time select options
        document.addEventListener('DOMContentLoaded', function () {
            var timeSelect = document.getElementById('heure');

            // Add morning time slots
            timeSlotsMorning.forEach(function (timeSlot) {
                var option = document.createElement('option');
                option.value = timeSlot;
                option.text = timeSlot;
                timeSelect.add(option);
            });

            // Add evening time slots
            timeSlotsEvening.forEach(function (timeSlot) {
                var option = document.createElement('option');
                option.value = timeSlot;
                option.text = timeSlot;
                timeSelect.add(option);
            });
        });
    </script>
</head>
<body>
    
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
    
<main>
<div class = "container-reservation" >

    <div class="formulaire_reservation">
        <p id="nom_restaurant"> Restaurant : ${restaurant.nom } </p>
   
       <input type="hidden" name="idRestaurant" value="${restaurant.id}">
   
         <input type="hidden" name="idUtilisateur" value="${utilisateur.id}">
      
      
      
      
        <form action="#" method="post">
            <input type="hidden" name="idUtilisateur" value="${utilisateur.id}">
            
            <label for="date">Date :</label>
            <input type="date" id="date" name="dateres" required>
            
          <select id="heure" name="heure" required>
                    
           </select>
            
            <label for="nbrPersonnes">Nombre de personnes :</label>
            <input type="number" id="nbrPersonnes" name="nbrPersonnes" required>
            
            <label for="commentaire">Commentaire :</label>
            <textarea id="commentaire" name="commentaire"></textarea>
            
            <button type="submit">Soumettre la réservation</button>
        </form>
    </div>

    <div class="reservation-image">
        <img src="images/restaurant.jpg" alt="pate d'or">
    </div>
    </div>
</main>
    
<%@ include file="/WEB-INF/fragments/footer.jspf" %>        
</body>
</html>
