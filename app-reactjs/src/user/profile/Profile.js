import React, { Component } from 'react';
import { getUserProfile } from '../../util/APIUtils';
import { formatDate } from '../../util/Helpers';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            isLoading: false
        }
        this.loadUserProfile = this.loadUserProfile.bind(this);
    }

    loadUserProfile(username) {
        this.setState({
            isLoading: true
        });

        getUserProfile(username)
        .then(response => {
            this.setState({
                user: response,
                isLoading: false
            });
        }).catch(error => {
            if(error.status === 404) {
                this.setState({
                    notFound: true,
                    isLoading: false
                });
            } else {
                this.setState({
                    serverError: true,
                    isLoading: false
                });        
            }
        });        
    }

    componentDidMount() {
        const username = this.props.match.params.username;
        this.loadUserProfile(username);
    }

    componentDidUpdate(nextProps) {
        if(this.props.match.params.username !== nextProps.match.params.username) {
            this.loadUserProfile(nextProps.match.params.username);
        }        
    }

    render() {
        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        return (
            <div className="profile">
                {
                    this.state.user ? (

                        <div className="user-profile">
                            <div >
                                <div className="user-summary">
                                    <div className="username">Username : {this.state.user.username}</div>
                                    <div className="username">Email : {this.state.user.email}</div>
                                    <div className="full-name">FullName : {this.state.user.employee.name}</div>
                                    <div className="username">Phone : {this.state.user.employee.phone}</div>
                                    <div className="username">Salary : {this.state.user.employee.salary}</div>
                                    <div className="username">Role : {this.state.user.employee.role}</div>
                                    <div className="user-joined">
                                        Joined {formatDate(this.state.user.joinedAt)}
                                    </div>
                                </div>
                            </div>
                        </div>
                    ): null               
                }
            </div>
        );
    }
}

export default Profile;