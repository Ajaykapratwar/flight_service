import { Component } from 'react';
import FlightRestService from '../services/flight-rest-service';

export default class FindCode extends Component {

    constructor(props) {
        super(props);
        this.service = new FlightRestService();
        this.state = {
            code: 0,
            flight: null
        }
    }   

    handleInput = (event) => {
        const value = event.target.value;
        this.setState({ code: value });
    }

    onSearch = () => {
        this.service.getFlightByCode(this.state.code).then(data => {
            this.setState({ flight: data });
        });
    }

    render() {
        return (
            <div>
                <br />
                <h2 className="text-custom">Find Flight by Code</h2>
                <hr />
                <input name='code' onChange={this.handleInput} className="form-control" placeholder="Enter flight code" />
                <button className="btn btn-custom mt-2" onClick={this.onSearch}>Search</button><br />
                <br />
                {this.state.flight ? (
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
                            <tr>
                                <td>{this.state.flight.code}</td>
                                <td>{this.state.flight.carrier}</td>
                                <td>{this.state.flight.source}</td>
                                <td>{this.state.flight.destination}</td>
                                <td>{this.state.flight.cost}</td>
                            </tr>
                        </tbody>
                    </table>
                ) : (
                    <div className="mt-3">No flights found.</div>
                )}
            </div>
        );
    }
}
