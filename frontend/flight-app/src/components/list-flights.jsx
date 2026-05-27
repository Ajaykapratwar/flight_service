import React, { Component } from 'react';
import FlightRestService from '../services/flight-rest-service';
import ShowFlight from './show-flight';

export default class ListFlights extends Component {

    constructor(props) {
        super(props);
        this.service = new FlightRestService();
        this.state = {
            flights: null
        };
    }

    componentDidMount() {
        this.getFlights();
    }

    getFlights() {
        this.service.getAllFlights().then(data => {
            this.setState({ flights: data });
        });
    }

    render() {
        if (!this.state.flights || this.state.flights.length === 0)
            return <div>Loading...</div>
        
        return (
            <div>
                <br />
                <h2 className="text-custom">List of Flights</h2>
                <table className="table table-striped table-bordered mt-3">
                    <thead className="table-dark">
                        <tr>
                            <th>Code</th><th>Carrier</th><th>Source</th><th>Destination</th><th>Cost</th><th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.flights.map((flight, index) => (
                        <tr key={index}>
                            <ShowFlight flight={flight} onDelete={this.doDelete} />
                        </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
    }

    doDelete = (code) => {
        this.service.deleteFlight(code).then(response => {
            this.getFlights();
        });
    }

}
