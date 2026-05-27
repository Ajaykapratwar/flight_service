import { Component } from 'react';
import FlightRestService from '../services/flight-rest-service';

export default class FindCarrier extends Component {

    constructor(props) {
        super(props);
        this.service = new FlightRestService();
        this.state = {
            carrier: '',
            flights: []
        }
    }   

    handleInput = (event) => {
        const value = event.target.value;
        this.setState({ carrier: value });
    }

    onSearch = () => {
        this.service.getFlightByCarrier(this.state.carrier).then(data => {
            this.setState({ flights: data });
        });
    }

    render() {
        return (
            <div>
                <br />
                <h2 className="text-custom">Find Flight by Carrier</h2>
                <hr />
                <input name='carrier' onChange={this.handleInput} className="form-control" placeholder="Enter carrier name" />
                <button className="btn btn-custom mt-2" onClick={this.onSearch}>Search</button><br />
                <br />
                {this.state.flights && this.state.flights.length > 0 ? (
                    <table className="table table-bordered mt-3">
                        <thead className="table-dark">
                            <tr>
                                <th>Code</th>
                                <th>Carrier</th>
                                <th>Source</th>
                                <th>Destination</th>
                                <th>Cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.flights.map((flight, index) => (
                                <tr key={index}>
                                    <td>{flight.code}</td>
                                    <td>{flight.carrier}</td>
                                    <td>{flight.source}</td>
                                    <td>{flight.destination}</td>
                                    <td>{flight.cost}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <div className="mt-3">No flights found for this carrier.</div>
                )}
            </div>
        );
    }
}
