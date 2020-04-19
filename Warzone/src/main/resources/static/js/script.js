window.addEventListener('load', evt =>{
var oReq = new XMLHttpRequest();
	
//	oReq.open('GET', 'http://localhost:8083/api/players');
//	oReq.send();
	console.log('Window loaded');
	console.log('oreq.responsetext ',oReq.responseText)
	init();
	
	
	var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			var dataDiv = document.getElementById('playerList');
            
			
			
            let data = JSON.parse(this.responseText);
            data.forEach(player => {
				console.log(player.name)
				let tr = document.createElement('tr');
				dataDiv.appendChild(tr);
				let td = document.createElement('td');
				td.textContent = player.id;
				let nameLi = document.createElement('td');
				nameLi.textContent = player.name;
				let killLi = document.createElement('td');
				killLi.textContent = player.killAmount;
				tr.appendChild(td);
				tr.appendChild(nameLi);
				tr.appendChild(killLi);

				
			});
            
            
        }
    };
    request.open('GET', 'http://localhost:8083/api/players');
    request.send();
});

function init(){
	console.log('In init()');
	console.log('form', document.createPlayer);
	document.createPlayer.newPlayer.addEventListener('click', function(event) {
		event.preventDefault();
		let form = document.createPlayer;
		let newPlayer = {
				id: form.id.value,
				name: form.playerName.value,
				killCount: form.killAmount.value,
				
		};
		// createFilm(film);
		console.log('clicked');
		console.log(newPlayer)
	});
}





	  
	

	