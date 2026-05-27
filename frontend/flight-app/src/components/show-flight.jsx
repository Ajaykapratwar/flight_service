import React, { Component } from 'react';

export default class ShowFlight extends Component {
    constructor(props) {
        super(props);
        this.state = {
            flight: props.flight
        };
    }

    render() {
        return (
            <> 
                <td className="text-custom">{this.state.flight.code}</td>
                <td className="text-custom">{this.state.flight.carrier}</td>
                <td className="text-custom">{this.state.flight.source}</td>
                <td className="text-custom">{this.state.flight.destination}</td>
                <td className="text-custom">{this.state.flight.cost}</td>
                <td>
                <button className="btn btn-custom btn-sm" onClick={() => this.onDelete()}>Delete</button>
                </td>
            </>
        );
    }

    onDelete() {
        if(window.confirm("Are you sure to delete flight: " + this.state.flight.code + "?"))
            this.props.onDelete(this.state.flight.code);
    }
}
