export default class FlightRestService {

    constructor() {
        this.url = "http://localhost:8080/api/v1/flights";
    }

    saveFlight(flight) {
        return fetch(this.url + "/add", {
            method: "POST",
            mode: "cors",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(flight)
        }).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).catch(error => {
            console.log(error.message);
        });
    }

    getFlightByCode(code) {
        return fetch(this.url + "/" + code).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).then(data => {   
            return data;
        })
        .catch(error => {
            console.log(error.message);
        });
    }

    getFlightByCarrier(carrier) {
        return fetch(this.url + "/carrier/" + carrier).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).then(data => {
            return data;
        })
        .catch(error => {
            console.log(error.message);
        });
    }   

    async getFlightByRoute(source, destination) {
        return await fetch(this.url + "/route?source=" + source + "&destination=" + destination).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).then(data => {
            return data;
        })
        .catch(error => {
            console.log(error.message);
        });
    }

    async getFlightByPriceRange(min, max) {
        return await fetch(this.url + "/price?min=" + min + "&max=" + max).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).then(data => {
            return data;
        }).catch(error => {
            console.log(error.message);
        });
    }  

    async getAllFlights() {
        return await fetch(this.url + "/list").then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.json();
        }).then(data => {
            return data;
        }).catch(error => {
            console.log(error.message);
        });
    }
    
    deleteFlight(code) {
        return fetch(this.url + "/" + code, {
            method: "DELETE",
            mode: "cors"
        }).then(response => {
            if (!response.ok) {
                this.handleResponseError(response);
            }
            return response.text();
        }).catch(error => {
            console.log(error.message);
        });
    }

    handleResponseError(response) {
        if (response.status === 404) {
            throw new Error("Resource not found");
        } else if (response.status === 500) {
            throw new Error("Internal server error");
        } else {
            throw new Error("Unexpected error: " + response.statusText);
        }
    }
}
