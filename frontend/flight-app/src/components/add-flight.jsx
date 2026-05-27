import React, { Component } from 'react';
import FlightRestService from '../services/flight-rest-service';

export default class AddFlight extends Component {

    constructor(props) {
        super(props);
        this.service = new FlightRestService();
        this.state = {
            code: 0,
            carrier: '',
            source: '',
            destination: '',
            cost: 0.0
        };
    }

    handleInput = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({
            [name]: value
        });
    }

    onSave() {
        this.service.saveFlight(this.state).then(() => {
            console.log("Flight Added Successfully");
        });
    }

    render() {
        return (
            <>
            <br />
            <h2 className="text-custom">Add New Flight</h2>
            <hr />
            <form className='was-validated' onSubmit={(e) => { e.preventDefault(); this.onSave(); }}>
                
                <input name="code" onChange={this.handleInput}  placeholder="Enter Flight Code"
                    className="form-control" required pattern="[1-9][0-9]*"/>
                <br />
                <input name="carrier" onChange={this.handleInput}  placeholder="Enter Carrier Name"
                    className="form-control" required pattern="[a-zA-Z\s]+"/>
                <br />
                <input name="source" onChange={this.handleInput}  placeholder="Enter Source"
                    className="form-control" required pattern="[a-zA-Z\s]+"/>
                <br />
                <input name="destination" onChange={this.handleInput}  placeholder="Enter Destination"
                    className="form-control" required pattern="[a-zA-Z\s]+"/>
                <br />
                <input name="cost" onChange={this.handleInput}  placeholder="Enter Flight Cost"
                    className="form-control" required pattern="[1-9][0-9]*(\.[0-9]+)?"/>
                <br />
                <button className="btn btn-custom" type='submit'>Save Flight</button>
            </form>
            </>
        );
    }
}
