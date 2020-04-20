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
            data.forEach(newPlayer => {
				console.log(newPlayer.name)
				let tr = document.createElement('tr');
				dataDiv.appendChild(tr);
				let td = document.createElement('td');
				td.textContent = newPlayer.id;
				let nameLi = document.createElement('td');
				nameLi.textContent = newPlayer.name;
				let killLi = document.createElement('td');
				killLi.textContent = newPlayer.killAmount;
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
				killAmount: form.killAmount.value,
				
			};
			createPlayer(newPlayer);
		
		console.log(newPlayer)
	});
}
function createPlayer(newPlayer) {
	let newPlayerJson = JSON.stringify(newPlayer);
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/players');
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			switch (xhr.status) {
			case 200:
			case 201:
				newPlayerJson = xhr.responseText;
				let newPlayer = JSON.parse(newPlayerJson);
				displayplayer(newPlayer);
				break;
			case 400:
				displayNotFound("Invalid newPlayer data: " + newPlayerJson);
				break;
			default:
				displayNotFound("Error occurred: " + xhr.status);
				break;
			}
		}
	}
	xhr.send(newPlayerJson);	
}






	  
	

	